package com.jolome.proyectofinal.ui.screens

import android.service.autofill.UserData
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jolome.proyectofinal.ui.theme.ProyectoFinalTheme

@Composable
fun AppContent(appContentData: AppContentData) {
    ProyectoFinalTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}

data class AppContentData(
    val navController: NavController,
    val title: (@Composable () -> Unit)?,
    val hasBackArrow:Boolean = false,
    val shouldAppear: Boolean,
    val content: @Composable ()->Unit
)
