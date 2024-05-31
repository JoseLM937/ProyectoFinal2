package com.jolome.proyectofinal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*
import com.jolome.proyectofinal.navigation.Routes

@Composable
fun PerfilScreen(navController: NavController) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val usuarioActual = firebaseAuth.currentUser
    val dbRealTime = FirebaseDatabase.getInstance("https://chat-4da02-default-rtdb.europe-west1.firebasedatabase.app/")

    var nombreUsuario by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }

    if (usuarioActual != null) {
        LaunchedEffect(usuarioActual.uid) {
            val nombreUsuarioRef = dbRealTime.getReference("users").child(usuarioActual.uid)
            val nombreUsuarioListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val nombre = dataSnapshot.child("username").getValue(String::class.java)
                    nombreUsuario = nombre ?: ""
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Manejar el error si la consulta falla
                }
            }
            nombreUsuarioRef.addListenerForSingleValueEvent(nombreUsuarioListener)
        }

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
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                enabled = isEditing
            )

            Text(text = "Email: ${usuarioActual.email}")

            Spacer(modifier = Modifier.height(16.dp))

            if (isEditing) {
                Button(
                    onClick = {
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nombreUsuario)
                            .build()

                        usuarioActual.updateProfile(profileUpdates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val userRef = dbRealTime.getReference("users").child(usuarioActual.uid)
                                    userRef.child("username").setValue(nombreUsuario)
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
                    navController.navigate(Routes.InicioSesion.route) {
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
