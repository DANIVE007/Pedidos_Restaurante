package com.example.roomdatabase

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.roomdatabase.data.Consumo
import com.example.roomdatabase.viewmodel.ConsumoViewModel

@Composable
fun IngresarConsumoScreen(
    navController: NavHostController,
    consumoViewModel: ConsumoViewModel,
    consumoParaEditar: Consumo? = null // Parámetro opcional para editar
) {
    var nombreItem by remember { mutableStateOf(consumoParaEditar?.nombreItem ?: "") }
    var precioUnitario by remember { mutableStateOf(consumoParaEditar?.precioUnitario?.toString() ?: "") }
    var cantidad by remember { mutableStateOf(consumoParaEditar?.cantidad?.toFloat() ?: 1f) }
    val esEdicion = consumoParaEditar != null // Detecta si estamos editando

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de nombre del producto
        OutlinedTextField(
            value = nombreItem,
            onValueChange = { nombreItem = it },
            label = { Text("Nombre del Producto") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de precio unitario
        OutlinedTextField(
            value = precioUnitario,
            onValueChange = { precioUnitario = it },
            label = { Text("Precio Unitario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        // Selector de cantidad de 1 a 99
        Text("Cantidad: ${cantidad.toInt()}")
        Slider(
            value = cantidad,
            onValueChange = { cantidad = it },
            valueRange = 1f..99f,
            modifier = Modifier.fillMaxWidth()
        )

        // Botón para volver atrás sin agregar o modificar
        Button(
            onClick = { navController.navigate("mostrar") }, // Navegar de vuelta a la pantalla principal
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar o modificar según la acción
        Button(
            onClick = {
                val precio = precioUnitario.toDoubleOrNull() ?: 0.0
                val nuevoConsumo = Consumo(
                    nombreItem = nombreItem,
                    precioUnitario = precio,
                    cantidad = cantidad.toInt()
                )

                if (esEdicion) {
                    // Modificar un consumo existente
                    consumoViewModel.update(nuevoConsumo.copy(id = consumoParaEditar!!.id))
                } else {
                    // Agregar nuevo consumo
                    consumoViewModel.insert(nuevoConsumo)
                }

                // Navegar de vuelta a la pantalla principal
                navController.navigate("mostrar")
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(if (esEdicion) "Modificar" else "Agregar")
        }
    }
}
