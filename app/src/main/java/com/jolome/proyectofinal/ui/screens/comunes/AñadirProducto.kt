package com.jolome.proyectofinal.ui.screens.comunes

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jolome.proyectofinal.navigation.Routes
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirProduct(navController: NavController) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    var nombreProducto by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    val dbRealTime = Firebase.database("https://chat-4da02-default-rtdb.europe-west1.firebasedatabase.app/")
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    val uid = currentUser?.uid

    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {
            imageUri = Uri.fromFile(File(context.externalCacheDir, "tempImage")) // Actualiza la URI de la imagen capturada
        }
    }

    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = uri // Actualiza la URI de la imagen seleccionada
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Añadir Producto") },
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (imageUri != null) {
                    // Muestra la imagen seleccionada
                    ImageFromUri(uri = imageUri!!, contentDescription = "Selected Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        pickImageLauncher.launch("image/*")
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text("Seleccionar imagen")
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    label = { Text(text = "Nombre del producto") },
                    value = nombreProducto,
                    onValueChange = { nombreProducto = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    label = { Text(text = "Descripción del producto") },
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (uid != null) {
                            val database = dbRealTime
                            val productsRef = database.getReference("users").child(uid).child("products")

                            val product = hashMapOf(
                                "nombre" to nombreProducto,
                                "descripcion" to descripcion,
                                "imagenUri" to imageUri.toString() // Guarda la URI como cadena de texto
                            )

                            productsRef.push().setValue(product)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Producto añadido con éxito", Toast.LENGTH_SHORT).show()
                                    navController.navigate(Routes.MainSrcreen.route)
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(context, "Error al añadir el producto: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Aceptar")
                }
            }
        }
    )
}

@Composable
fun ImageFromUri(uri: Uri, contentDescription: String) {
    val painter: Painter = rememberImagePainter(uri)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}







