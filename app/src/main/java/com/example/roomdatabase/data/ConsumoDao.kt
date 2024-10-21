package com.example.roomdatabase.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ConsumoDao {

    @Query("SELECT * FROM consumos ORDER BY id ASC")
    fun getAllConsumos(): Flow<List<Consumo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertConsumo(consumo: Consumo)

    @Update
    suspend fun updateConsumo(consumo: Consumo)

    @Delete
    suspend fun deleteConsumo(consumo: Consumo)

    @Query("DELETE FROM consumos")
    suspend fun deleteAllConsumos()

    // Método para obtener un consumo por su ID
    @Query("SELECT * FROM consumos WHERE id = :id LIMIT 1")
    suspend fun getConsumoById(id: Int): Consumo?
}
