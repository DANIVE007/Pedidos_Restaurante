package com.example.roomdatabase

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.roomdatabase.data.Consumo
import com.example.roomdatabase.viewmodel.ConsumoViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun MostrarConsumosScreen(navController: NavHostController, consumoViewModel: ConsumoViewModel) {
    val consumos by consumoViewModel.allConsumos.collectAsState(initial = emptyList())
    val total = consumos.sumOf { it.precioUnitario * it.cantidad }



    // Formatea el total usando Locale chileno
    val formatoCL = NumberFormat.getNumberInstance(Locale("es", "CL"))
    val totalFormateado = formatoCL.format(total)




    Scaffold(
        floatingActionButton = {
            // Ajusta el padding para que el botón no cubra el total
            FloatingActionButton(
                onClick = { navController.navigate("ingresar") },
                modifier = Modifier.
                padding(bottom = 80.dp)


            ) {
                Text(
                text ="Nuevo Consumo",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp) // Padding interno del texto
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp) // Padding alrededor de toda la lista
                .padding(top = 16.dp)

            ) {
                items(consumos) { consumo ->
                    ConsumoItem(
                        consumo = consumo,
                        onDelete = { consumoViewModel.delete(consumo) },
                        onUpdate = { navController.navigate("editar/${consumo.id}") }
                    )
                    Divider()
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Añade espacio entre la lista y el total

            // Mostrar el total en la parte inferior derecha
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = "Total: $$totalFormateado", style = MaterialTheme.typography.h6)
            }
        }
    }
}

@Composable
fun ConsumoItem(consumo: Consumo, onDelete: () -> Unit, onUpdate: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${consumo.nombreItem} (${consumo.cantidad} x ${consumo.precioUnitario})")

        Row {
            IconButton(onClick = onUpdate) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar Consumo")
            }

            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar Consumo")
            }
        }
    }
}
