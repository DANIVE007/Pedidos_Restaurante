package com.example.roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consumos")
data class Consumo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreItem: String,
    val precioUnitario: Double,
    val cantidad: Int
) {
    // Método para calcular el total
    fun calcularTotal(): Double {
        return precioUnitario * cantidad
    }
}
