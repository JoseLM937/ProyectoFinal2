package com.jolome.proyectofinal.ui.screens.comunes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

data class Product(
    val id: String? = null,
    val nombre: String? = null,
    val descripcion: String? = null,
    val imageUrl: String? = null,
    val userId: String? = null // Añade este campo para almacenar el ID del usuario
)

@Composable
fun fetchProducts(): List<Product> {
    // Estado mutable para almacenar la lista de productos
    val products = remember { mutableStateOf<List<Product>>(emptyList()) }

    // Obtén una referencia a la base de datos Firebase
    val database = Firebase.database("https://chat-4da02-default-rtdb.europe-west1.firebasedatabase.app/")
    val productsRef = database.getReference("users")

    // Utiliza LaunchedEffect para realizar la operación de lectura de datos en un hilo de fondo
    LaunchedEffect(Unit) {
        // Agrega un evento de escucha a la referencia de los productos en la base de datos
        productsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Lista mutable para almacenar los productos recuperados de la base de datos
                val productList = mutableListOf<Product>()

                // Itera sobre cada nodo de usuario en la base de datos
                snapshot.children.forEach { userSnapshot ->
                    // Itera sobre cada producto bajo el nodo de usuario
                    userSnapshot.child("products").children.forEach { productSnapshot ->
                        // Obtiene los datos del producto del snapshot y crea un objeto Product
                        val id = productSnapshot.key // La clave del snapshot es el ID del producto
                        val nombre = productSnapshot.child("nombre").getValue(String::class.java)
                        val descripcion = productSnapshot.child("descripcion").getValue(String::class.java)
                        val imageUrl = productSnapshot.child("imageUrl").getValue(String::class.java)

                        // Agrega el producto a la lista de productos
                        productList.add(Product(id, nombre, descripcion, imageUrl))
                    }
                }

                // Actualiza el estado con la lista de productos recuperados
                products.value = productList
            }

            override fun onCancelled(error: DatabaseError) {
                // Maneja cualquier error que ocurra durante la recuperación de datos
                // Puedes agregar registro de errores o mostrar un mensaje al usuario
            }
        })
    }

    // Devuelve la lista de productos
    return products.value
}



