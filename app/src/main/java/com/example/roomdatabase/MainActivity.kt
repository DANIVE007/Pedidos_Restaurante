package com.example.roomdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.roomdatabase.navigation.AppNavHost
import com.example.roomdatabase.ui.theme.RoomDatabaseTheme
import com.example.roomdatabase.viewmodel.ConsumoViewModel
import com.example.roomdatabase.viewmodel.ConsumoViewModelFactory

class MainActivity : ComponentActivity() {

    // Inicializa el ViewModel usando viewModels y ConsumoViewModelFactory
    private val consumoViewModel: ConsumoViewModel by viewModels {
        ConsumoViewModelFactory((application as ConsumoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDatabaseTheme {
                // Inicializa el NavController
                val navController = rememberNavController()

                // Llama a AppNavHost y pasa navController y consumoViewModel
                AppNavHost(navController = navController, consumoViewModel = consumoViewModel)
            }
        }
    }
}

