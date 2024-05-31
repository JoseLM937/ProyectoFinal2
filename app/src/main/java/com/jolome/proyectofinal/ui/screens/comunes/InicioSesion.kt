package com.jolome.proyectofinal.ui.screens.comunes

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jolome.proyectofinal.navigation.Routes

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import android.content.Context

private const val SHARED_PREF_NAME = "MyAppPreferences"
private const val KEY_IS_LOGGED_IN = "isLoggedIn"

@Composable
fun InicioSesion(navController: NavController) {
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(
            text = "Inicia sesión",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp) // Agrega espacio alrededor del título
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            label = { Text(text = "Email") },
            value = user,
            onValueChange = { user = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    val auth = FirebaseAuth.getInstance()
                    auth.signInWithEmailAndPassword(user, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                saveLoginState(context, true)
                                navController.navigate(Routes.MainSrcreen.route)
                                Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Error al iniciar sesión: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                modifier = Modifier
                    .weight(1f) // Utiliza weight para distribuir el espacio de manera uniforme
                    .padding(8.dp)
            ) {
                Text(text = "Iniciar sesión")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { navController.navigate(Routes.Registro.route) },
                modifier = Modifier
                    .weight(1f) // Utiliza weight para distribuir el espacio de manera uniforme
                    .padding(8.dp)
            ) {
                Text(text = "Registrarse")
            }
        }
    }
}

private fun saveLoginState(context: Context, isLoggedIn: Boolean) {
    val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
}