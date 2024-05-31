package com.jolome.proyectofinal.ui.screens.comunes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AcercaDe(navController: NavController){
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(text = "Descripción de la aplicación:\n" +
                "\n" +
                "\"Nuestra aplicación, PepiPop, es una plataforma diseñada para brindar una experiencia segura y conveniente en la compra y venta de productos tecnológicos. Desde teléfonos inteligentes y computadoras portátiles hasta dispositivos electrónicos de última generación, PepiPop conecta a compradores y vendedores de manera eficiente, ofreciendo una amplia variedad de productos tecnológicos para satisfacer las necesidades de los usuarios más exigentes.\n" +
                "\n" +
                "Con una interfaz intuitiva y funciones avanzadas de búsqueda, PepiPop facilita la exploración y la selección de productos, permitiendo a los usuarios encontrar exactamente lo que están buscando en cuestión de segundos. Además, nuestra plataforma garantiza transacciones seguras y protegidas, brindando tranquilidad tanto a compradores como a vendedores durante todo el proceso.\n" +
                "\n" +
                "Únete a nuestra comunidad en línea de entusiastas de la tecnología y descubre una nueva forma de comprar y vender productos tecnológicos de manera confiable y sin complicaciones con PepiPop.\"\n" +
                "\n" +
                "Historia y misión:\n" +
                "\n" +
                "\"La historia de PepiPop se remonta a la visión de un equipo apasionado por la tecnología que se propuso crear una solución innovadora para simplificar el proceso de compra y venta de productos tecnológicos. Con el objetivo de proporcionar una plataforma segura y eficiente, nació PepiPop, con la misión de transformar la experiencia de comercio electrónico en el mercado tecnológico.\n" +
                "\n" +
                "Nuestra misión es ofrecer una alternativa confiable y transparente en el mercado de productos tecnológicos, fomentando la confianza entre compradores y vendedores y promoviendo la accesibilidad a la tecnología para todos. Nos esforzamos por brindar un entorno en el que los usuarios puedan realizar transacciones con total seguridad y comodidad, respaldados por un equipo comprometido con la excelencia y la satisfacción del cliente.\n" +
                "\n" +
                "En PepiPop, creemos en el poder de la tecnología para mejorar la vida de las personas y estamos dedicados a facilitar el acceso a los últimos avances tecnológicos de manera justa y transparente. ¡Únete a nosotros en nuestro viaje para transformar la experiencia de compra y venta de productos tecnológicos!",
            style = MaterialTheme.typography.bodyLarge
            )

    }
}