package com.jolome.proyectofinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.jolome.proyectofinal.ThemeViewModel
import com.jolome.proyectofinal.ui.screens.comunes.Registro
import com.jolome.proyectofinal.ui.screens.AppContent
import com.jolome.proyectofinal.ui.screens.AppContentData
import com.jolome.proyectofinal.ui.screens.PerfilScreen
import com.jolome.proyectofinal.ui.screens.SplashScreen
import com.jolome.proyectofinal.ui.screens.comunes.AcercaDe
import com.jolome.proyectofinal.ui.screens.comunes.Ajust
import com.jolome.proyectofinal.ui.screens.comunes.AñadirProduct
import com.jolome.proyectofinal.ui.screens.comunes.DetallesProducto
import com.jolome.proyectofinal.ui.screens.comunes.InicioSesion
import com.jolome.proyectofinal.ui.screens.comunes.MainScreen
import com.jolome.proyectofinal.ui.screens.comunes.ProductosGuardados
import com.jolome.proyectofinal.ui.screens.comunes.SavedProductsViewModel
import com.jolome.proyectofinal.ui.screens.comunes.politicaPrivacidad
import com.jolome.proyectofinal.ui.screens.comunes.terminosServicio


@Composable
fun Navigation(themeViewModel: ThemeViewModel, savedProductsViewModel: SavedProductsViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.SplashScreen.route
    ){

        composable(
            route = Routes.SplashScreen.route
        ){
            SplashScreen(navController)
        }

        composable(
            route = Routes.Registro.route
        ){
            /*AppContent(
                AppContentData(
                    navController = navController,
                    shouldAppear = true,
                    title = null,
                    content = {
                        Registro(navController = navController)
                    }
                )
            )*/
            
            Registro(navController = navController)
        }

        composable(
            route = Routes.InicioSesion.route
        ){
            InicioSesion(navController = navController)
        }

        composable(
            route = Routes.MainSrcreen.route
        ){
            MainScreen(navController = navController, savedProductsViewModel = savedProductsViewModel)
        }

        composable(
            route = Routes.Screeen.route
        ){
        }


        composable(
            route = Routes.PerfilScreen.route
        ){
            PerfilScreen(navController = navController)
        }

        composable(
            route = Routes.AñadirProducto.route
        ){
            AñadirProduct(navController = navController)
        }

        composable(
            route = Routes.PoliticaPrivacidad.route
        ){
            politicaPrivacidad(navController = navController)
        }

        composable(
            route = Routes.TerminosServicio.route
        ){
            terminosServicio(navController = navController)
        }

        composable(
            route = Routes.Ajustes.route
        ){
            Ajust(navController = navController, themeViewModel = themeViewModel)
        }

        composable(
            route = Routes.DetallesProducto.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            DetallesProducto(productId = productId, navController = navController)
        }

        composable(Routes.ProductosGuardados.route) {
            val firebaseAuth = FirebaseAuth.getInstance()
            val currentUser = firebaseAuth.currentUser

            ProductosGuardados(
                navController = navController,
                savedProductsViewModel = savedProductsViewModel
            )
        }
        
        composable(
            route = Routes.AcercaDe.route
            
        ){
            AcercaDe(navController = navController)
        }



    }
}