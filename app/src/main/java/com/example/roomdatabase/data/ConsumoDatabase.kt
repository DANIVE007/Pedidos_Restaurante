package com.example.roomdatabase.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Consumo::class], version = 1, exportSchema = false)
abstract class ConsumoDatabase : RoomDatabase() {
    abstract fun consumoDao(): ConsumoDao
}
