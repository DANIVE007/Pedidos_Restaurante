package com.example.roomdatabase.repository

import com.example.roomdatabase.data.Consumo
import com.example.roomdatabase.data.ConsumoDao
import kotlinx.coroutines.flow.Flow

class ConsumoRepository(private val consumoDao: ConsumoDao) {

    val allConsumos: Flow<List<Consumo>> = consumoDao.getAllConsumos()

    suspend fun insert(consumo: Consumo) {
        consumoDao.insertConsumo(consumo)
    }

    suspend fun update(consumo: Consumo) {
        consumoDao.updateConsumo(consumo)
    }

    suspend fun delete(consumo: Consumo) {
        consumoDao.deleteConsumo(consumo)
    }

    suspend fun deleteAll() {
        consumoDao.deleteAllConsumos()
    }

    // Método para obtener un consumo por su ID
    suspend fun getConsumoById(id: Int?): Consumo? {
        return id?.let { consumoDao.getConsumoById(it) }
    }
}
