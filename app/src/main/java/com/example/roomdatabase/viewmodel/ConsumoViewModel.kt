package com.example.roomdatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.data.Consumo
import com.example.roomdatabase.repository.ConsumoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ConsumoViewModel(private val repository: ConsumoRepository) : ViewModel() {

    val allConsumos: Flow<List<Consumo>> = repository.allConsumos

    fun insert(consumo: Consumo) = viewModelScope.launch {
        repository.insert(consumo)
    }

    fun update(consumo: Consumo) = viewModelScope.launch {
        repository.update(consumo)
    }

    fun delete(consumo: Consumo) = viewModelScope.launch {
        repository.delete(consumo)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    // Método para obtener un consumo por su ID
    fun getConsumoById(id: Int?): Consumo? = runBlocking {
        repository.getConsumoById(id)
    }
}
