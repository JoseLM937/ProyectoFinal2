package com.jolome.proyectofinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jolome.proyectofinal.R
import com.jolome.proyectofinal.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true){
        //Aquí deberíamos hacer la carga del sistema.
        //Consultar una BDD, acceder a una API, etc..
        //Lo simulamos con un delay de 5s
        delay(5000)
        //Lo quitamos de la pila por si el usuario le da a volver no vuelva al SplashScreen
        navController.popBackStack()
        navController.navigate(Routes.InicioSesion.route)
    }

    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bolsitatienda),
            contentDescription = "logo",
            modifier = Modifier.size(200.dp,150.dp),
            contentScale = ContentScale.Fit


        )
        Text(text = "Bienvenidos",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )

    }
}