package com.jolome.proyectofinal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseApp
import com.jolome.proyectofinal.navigation.Navigation
import com.jolome.proyectofinal.ui.theme.ProyectoFinalTheme
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jolome.proyectofinal.navigation.Routes
import com.jolome.proyectofinal.ui.screens.comunes.SavedProductsViewModel

private const val SHARED_PREF_NAME = "MyAppPreferences"
private const val KEY_IS_LOGGED_IN = "isLoggedIn"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val savedProductsViewModel: SavedProductsViewModel = viewModel()

            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            ProyectoFinalTheme(darkTheme = isDarkTheme) {
                val sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                val isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)

                if (isLoggedIn) {
                    Navigation(themeViewModel = themeViewModel, savedProductsViewModel = savedProductsViewModel)
                } else {
                    // Redirigir al usuario a la pantalla de inicio de sesión si no está logueado
                    startActivity(Intent(this@MainActivity, Routes.InicioSesion::class.java))
                    finish() // Finaliza esta actividad para que el usuario no pueda volver atrás presionando el botón de retroceso
                }
            }
        }
    }
}

class ThemeViewModel : ViewModel() {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    fun toggleTheme() {
        viewModelScope.launch {
            _isDarkTheme.value = !_isDarkTheme.value
        }
    }

    fun setTheme(isDark: Boolean) {
        viewModelScope.launch {
            _isDarkTheme.value = isDark
        }
    }
}