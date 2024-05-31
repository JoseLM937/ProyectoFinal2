package com.jolome.proyectofinal.ui.screens.comunes

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.database
import androidx.compose.runtime.getValue
import com.google.firebase.database.ktx.database
import androidx.compose.material3.Text // Import corrected

import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import coil.compose.rememberImagePainter
import com.google.firebase.database.ktx.database

import coil.compose.rememberImagePainter
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun DetallesProducto(productId: String, navController: NavController) {
    val context = LocalContext.current
    val database = Firebase.database("https://chat-4da02-default-rtdb.europe-west1.firebasedatabase.app/")
    val productRef = database.getReference("products").child(productId)
    var product by remember { mutableStateOf<Product?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(productId) {
        productRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                product = snapshot.getValue(Product::class.java)
                Log.d("DetallesProducto", "Producto obtenido: $product")
            } else {
                Log.d("DetallesProducto", "No se encontró el producto para el ID: $productId")
            }
            loading = false
        }.addOnFailureListener { e ->
            Toast.makeText(context, "Error al obtener el producto: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("DetallesProducto", "Error al obtener el producto: ${e.message}", e)
            loading = false
        }
    }

    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Cargando...", fontSize = 18.sp)
        }
    } else {
        if (product != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Nombre: ${product!!.nombre}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Descripción: ${product!!.descripcion}", fontSize = 18.sp)
                product!!.imageUrl?.let { imageUrl ->
                    Image(
                        painter = rememberImagePainter(imageUrl),
                        contentDescription = product!!.nombre,
                        modifier = Modifier
                            .size(200.dp)
                            .aspectRatio(1f)
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Producto no encontrado", fontSize = 18.sp)
            }
        }
    }
}



