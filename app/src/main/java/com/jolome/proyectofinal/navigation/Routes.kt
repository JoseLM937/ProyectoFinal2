package com.jolome.proyectofinal.navigation

sealed class Routes(val route:String) {
    object SplashScreen: Routes("splash_screen")
    object Registro: Routes("Registro")
    object InicioSesion: Routes("InicioSesion")
    object MainSrcreen: Routes("MainScreen")
    object Screeen: Routes("Screeen")
    object PerfilScreen: Routes("PerfilScreen")
    object AñadirProducto: Routes("AñadirProducto")
    object PoliticaPrivacidad: Routes("PoliticaPrivacidad")
    object TerminosServicio: Routes("TerminosServicio")
    object Ajustes: Routes("Ajustes")
    object DetallesProducto : Routes("detalles_producto/{productId}") {
        fun createRoute(productId: String) = "detalles_producto/$productId"
    }
    object ProductosGuardados : Routes("productosguardados")
    object AcercaDe: Routes("AcercaDe")

}