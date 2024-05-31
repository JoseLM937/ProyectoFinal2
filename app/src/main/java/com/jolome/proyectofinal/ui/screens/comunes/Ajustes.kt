package com.jolome.proyectofinal.ui.screens.comunes

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jolome.proyectofinal.ThemeViewModel
import androidx.compose.foundation.layout.fillMaxSize
import com.jolome.proyectofinal.navigation.Routes
import com.jolome.proyectofinal.ui.theme.ProyectoFinalTheme

@Composable
fun Ajust(navController: NavController, themeViewModel: ThemeViewModel) {
    val isDarkThemeEnabled by themeViewModel.isDarkTheme.collectAsState()
    var notificationsEnabled by remember { mutableStateOf(true) }

    ProyectoFinalTheme(darkTheme = isDarkThemeEnabled) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Ajustes",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Divider()

                // Dark Theme Toggle
                SettingItem(
                    title = "Tema oscuro",
                    description = "Habilitar o deshabilitar el tema oscuro",
                    checked = isDarkThemeEnabled,
                    onCheckedChange = {
                        themeViewModel.toggleTheme()
                    }
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Notifications Toggle
                SettingItem(
                    title = "Notificaciones",
                    description = "Habilitar o deshabilitar las notificaciones",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // About Section
                SettingItem(
                    title = "Acerca de",
                    description = "Ver información sobre la aplicación",
                    onClick = {
                        navController.navigate(Routes.AcercaDe.route)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingItem(
    title: String,
    description: String,
    checked: Boolean? = null,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    ListItem(
        headlineContent = { Text(text = title) },
        supportingContent = { Text(text = description) },
        trailingContent = {
            when {
                checked != null && onCheckedChange != null -> {
                    Switch(
                        checked = checked,
                        onCheckedChange = onCheckedChange
                    )
                }
                onClick != null -> {
                    IconButton(onClick = onClick) {
                        Icon(Icons.Default.ArrowForward, contentDescription = null)
                    }
                }
            }
        },
        modifier = Modifier
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
    )
}