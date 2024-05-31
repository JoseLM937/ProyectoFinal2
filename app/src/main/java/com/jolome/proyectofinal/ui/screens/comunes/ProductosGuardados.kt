package com.jolome.proyectofinal.ui.screens.comunes

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.jolome.proyectofinal.R
import com.jolome.proyectofinal.navigation.Routes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductosGuardados(navController: NavController, savedProductsViewModel: SavedProductsViewModel) {
    var savedProducts by remember { mutableStateOf<List<Product>>(emptyList()) }

    LaunchedEffect(Unit) {
        savedProductsViewModel.loadSavedProducts()
    }

    // Observe the saved products state from the view model
    savedProducts = savedProductsViewModel.savedProducts.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Productos Guardados") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(savedProducts) { product ->
                SavedProductItem(
                    product = product,
                    onProductClick = { productId ->
                        navController.navigate(Routes.DetallesProducto.createRoute(productId))
                    },
                    onRemoveProduct = { savedProductsViewModel.removeProduct(it) }
                )
            }
        }
    }
}

@Composable
fun SavedProductItem(
    product: Product,
    onProductClick: (String) -> Unit,
    onRemoveProduct: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { product.id?.let { onProductClick(it) } },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            product.imageUrl?.let { imageUrl ->
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.nombre ?: "", style = MaterialTheme.typography.bodyLarge)
                Text(text = product.descripcion ?: "", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { onRemoveProduct(product) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove from cart", tint = Color.Red)
            }
        }
    }
}

