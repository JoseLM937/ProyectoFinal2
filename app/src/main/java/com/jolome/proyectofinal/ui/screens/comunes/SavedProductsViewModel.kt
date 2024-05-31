package com.jolome.proyectofinal.ui.screens.comunes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SavedProductsViewModel : ViewModel() {
    private val _savedProducts = MutableStateFlow<List<Product>>(emptyList())
    val savedProducts: StateFlow<List<Product>> = _savedProducts

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val userId: String?
        get() = firebaseAuth.currentUser?.uid

    init {
        loadSavedProducts()
    }

    fun loadSavedProducts() {
        viewModelScope.launch {
            userId?.let { uid ->
                try {
                    val products = firestore.collection("users")
                        .document(uid)
                        .collection("savedProducts")
                        .get()
                        .await()
                        .toObjects(Product::class.java)
                    _savedProducts.value = products
                } catch (e: Exception) {
                    // Handle exception
                }
            }
        }
    }

    fun onAddToCart(product: Product) {
        userId?.let { uid ->
            viewModelScope.launch {
                try {
                    firestore.collection("users")
                        .document(uid)
                        .collection("savedProducts")
                        .document(product.id!!)
                        .set(product)
                        .await()
                    // No es necesario actualizar _savedProducts aquí, ya que se actualiza automáticamente en loadSavedProducts()
                } catch (e: Exception) {
                    // Handle exception
                }
            }
        }
    }

    fun removeProduct(product: Product) {
        userId?.let { uid ->
            viewModelScope.launch {
                try {
                    firestore.collection("users")
                        .document(uid)
                        .collection("savedProducts")
                        .document(product.id!!)
                        .delete()
                        .await()
                    // No es necesario actualizar _savedProducts aquí, ya que se actualiza automáticamente en loadSavedProducts()
                } catch (e: Exception) {
                    // Handle exception
                }
            }
        }
    }
}