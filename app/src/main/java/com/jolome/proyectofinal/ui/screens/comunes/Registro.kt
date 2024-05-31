package com.jolome.proyectofinal.ui.screens.comunes

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jolome.proyectofinal.navigation.Routes
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import android.content.Context


private const val SHARED_PREF_NAME = "MyAppPreferences"
private const val KEY_IS_LOGGED_IN = "isLoggedIn"

@Composable
fun Registro(navController: NavController) {
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val dbRealTime = Firebase.database("https://chat-4da02-default-rtdb.europe-west1.firebasedatabase.app/")

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Crea una nueva cuenta",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp) // Agrega espacio alrededor del título
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            label = { Text(text = "User") },
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
        TextField(
            label = { Text(text = "Email") },
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val currentUser = FirebaseAuth.getInstance().currentUser
                            val uid = currentUser?.uid
                            val database = dbRealTime
                            val usersRef = database.getReference("users")

                            // Guardar el nombre de usuario en la base de datos
                            usersRef.child(uid!!).child("username").setValue(user)

                            saveLoginState(context, true)
                            navController.navigate(Routes.MainSrcreen.route)
                            Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error al registrar usuario: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) // Agrega espacio alrededor del botón
        ) {
            Text(text = "Registrarse")
        }
    }
}


private fun saveLoginState(context: Context, isLoggedIn: Boolean) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
}

