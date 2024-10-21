package com.example.roomdatabase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.roomdatabase.MostrarConsumosScreen
import com.example.roomdatabase.IngresarConsumoScreen
import com.example.roomdatabase.viewmodel.ConsumoViewModel
import com.example.roomdatabase.data.Consumo

@Composable
fun AppNavHost(navController: NavHostController, consumoViewModel: ConsumoViewModel) {
    NavHost(navController = navController, startDestination = "mostrar") {
        composable("mostrar") {
            MostrarConsumosScreen(navController = navController, consumoViewModel = consumoViewModel)
        }
        composable("ingresar") {
            IngresarConsumoScreen(navController = navController, consumoViewModel = consumoViewModel)
        }
        // Ruta para editar consumo, donde se pasa el consumo a editar
        composable("editar/{consumoId}") { backStackEntry ->
            val consumoId = backStackEntry.arguments?.getString("consumoId")?.toIntOrNull()
            val consumoParaEditar = consumoViewModel.getConsumoById(consumoId) // Obtener el consumo por ID
            IngresarConsumoScreen(
                navController = navController,
                consumoViewModel = consumoViewModel,
                consumoParaEditar = consumoParaEditar
            )
        }
    }
}
