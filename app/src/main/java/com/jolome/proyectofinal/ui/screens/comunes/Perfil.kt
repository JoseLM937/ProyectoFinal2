package com.jolome.proyectofinal.ui.screens.comunes

import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.*
import com.google.firebase.auth.UserProfileChangeRequest

@Composable
fun perfil(navController: NavController) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val usuarioActual = firebaseAuth.currentUser

    var nombre by remember { mutableStateOf(usuarioActual?.displayName ?: "") }
    var email by remember { mutableStateOf(usuarioActual?.email ?: "") }
    var isEditing by remember { mutableStateOf(false) }

    if (usuarioActual != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Perfil de Usuario",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                enabled = isEditing
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false // No permitimos cambiar el email en este ejemplo
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isEditing) {
                Button(
                    onClick = {
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nombre)
                            .build()

                        usuarioActual.updateProfile(profileUpdates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Actualización exitosa
                                    isEditing = false
                                }
                            }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Guardar")
                }
            } else {
                Button(
                    onClick = { isEditing = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Editar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    firebaseAuth.signOut()
                    // Navegar a la pantalla de inicio de sesión
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cerrar sesión", color = Color.White)
            }
        }
    } else {
        // Manejar el caso en el que no hay un usuario autenticado
        Text(text = "No hay usuario autenticado")
    }
}