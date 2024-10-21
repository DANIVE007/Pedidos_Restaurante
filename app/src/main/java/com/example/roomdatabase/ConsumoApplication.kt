package com.example.roomdatabase

import com.example.roomdatabase.data.ConsumoDatabaseInstance
import com.example.roomdatabase.repository.ConsumoRepository
import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ConsumoApplication : Application() {

    // Crear un Job de supervisor para la aplicación
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Crear una instancia del repositorio
    val repository: ConsumoRepository by lazy {
        val database = ConsumoDatabaseInstance.getDatabase(this, applicationScope)
        ConsumoRepository(database.consumoDao())
    }
}
