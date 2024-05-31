package com.jolome.proyectofinal.ui.screens.comunes



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.jolome.proyectofinal.navigation.Routes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

import androidx.compose.material3.Card
import androidx.compose.ui.graphics.Color
import com.google.firebase.auth.FirebaseAuth

sealed class OptionsMain(val title: String, val icon: ImageVector) {
    object Option1 : OptionsMain("Inicio", Icons.Default.Home)
    object Option2 : OptionsMain("Política de Privacidad", Icons.Default.Person)
    object Option3 : OptionsMain("Términos de Servicio", Icons.Default.Notifications)
    object Option4 : OptionsMain("Configuración", Icons.Default.Settings)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, savedProductsViewModel: SavedProductsViewModel) {
    val optionsMain = listOf(
        OptionsMain.Option1,
        OptionsMain.Option2,
        OptionsMain.Option3,
        OptionsMain.Option4
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableStateOf(optionsMain[0].title) }
    val products = fetchProducts() // Asegúrate de que fetchProducts() obtenga los datos correctamente

    var showProfileButton by remember { mutableStateOf(true) }
    var showAddProductButton by remember { mutableStateOf(true) }

    var searchQuery by remember { mutableStateOf("") }
    val filteredProducts = products.filter {
        it.nombre?.contains(searchQuery, ignoreCase = true) == true ||
                it.descripcion?.contains(searchQuery, ignoreCase = true) == true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "PepiPop") },
                    navigationIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            optionsMain.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOption = option.title
                                        expanded = false
                                        when (option) {
                                            OptionsMain.Option1 -> navController.navigate(Routes.MainSrcreen.route)
                                            OptionsMain.Option2 -> navController.navigate(Routes.PoliticaPrivacidad.route)
                                            OptionsMain.Option3 -> navController.navigate(Routes.TerminosServicio.route)
                                            OptionsMain.Option4 -> navController.navigate(Routes.Ajustes.route)
                                        }
                                    },
                                    text = { Text(text = option.title) }
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(Routes.ProductosGuardados.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Productos guardados")
                        }
                        if (showProfileButton) {
                            IconButton(onClick = { navController.navigate(Routes.PerfilScreen.route) }) {
                                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Perfil")
                            }
                        }
                        if (showAddProductButton) {
                            IconButton(onClick = { navController.navigate(Routes.AñadirProducto.route) }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir producto")
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Buscar productos") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(filteredProducts) { product ->
                        ProductItem(
                            product = product,
                            onProductClick = { productId ->
                                navController.navigate(Routes.DetallesProducto.createRoute(productId))
                            },
                            onAddToCart = { savedProductsViewModel.onAddToCart(product) }
                        )
                    }
                }
            }
        }
    }
}

// En ProductItem.kt
@Composable
fun ProductItem(
    product: Product,
    onProductClick: (String) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { product.id?.let { onProductClick(it) } }, // Llamar a onProductClick con el id del producto
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
            IconButton(onClick = { onAddToCart(product) }) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Add to cart", tint = Color.Gray)
            }
        }
    }
}



