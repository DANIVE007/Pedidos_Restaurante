package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object ConsumoDatabaseInstance {
    @Volatile
    private var INSTANCE: ConsumoDatabase? = null

    fun getDatabase(context: Context, scope: CoroutineScope): ConsumoDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ConsumoDatabase::class.java,
                "consumos_database"
            )
                .fallbackToDestructiveMigration()
                .addCallback(ConsumoDatabaseCallback(scope))
                .build()
            INSTANCE = instance
            instance
        }
    }

    private class ConsumoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Prepopular la base de datos
            INSTANCE?.let { database ->
                scope.launch {
                    prepopulateDatabase(database.consumoDao())
                }
            }
        }

        suspend fun prepopulateDatabase(consumoDao: ConsumoDao) {
            // Definir los consumos iniciales sin IDs
            val initialConsumos = listOf(
                Consumo(nombreItem = "Cerveza", precioUnitario = 5.0, cantidad = 1),
                Consumo(nombreItem = "Hamburguesa", precioUnitario = 8.0, cantidad = 2)
            )
            initialConsumos.forEach { consumo ->
                consumoDao.insertConsumo(consumo)
            }
        }
    }
}
