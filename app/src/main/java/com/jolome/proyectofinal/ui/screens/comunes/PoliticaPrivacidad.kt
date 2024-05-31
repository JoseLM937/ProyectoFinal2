package com.jolome.proyectofinal.ui.screens.comunes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun politicaPrivacidad(navController: NavController){

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(text = "Política de Privacidad\n" +
                "1. Introducción\n" +
                "Bienvenido a [Nombre de la Aplicación], operada por [Nombre de la Empresa] (en adelante, \"nosotros\" o \"nuestro\"). Nos comprometemos a proteger y respetar su privacidad. Esta política de privacidad explica cómo recopilamos, usamos, compartimos y protegemos sus datos personales cuando usa nuestra aplicación de compra y venta de artículos de segunda mano.\n" +
                "\n" +
                "2. Responsable del Tratamiento de Datos\n" +
                "[Nombre de la Empresa] es el responsable del tratamiento de los datos personales que se recogen a través de nuestra aplicación. Nuestra sede está ubicada en [Dirección Completa]. Puede contactarnos en [Correo Electrónico de Contacto] para cualquier consulta relacionada con esta política de privacidad.\n" +
                "\n" +
                "3. Datos Personales que Recopilamos\n" +
                "Datos de Registro:\n" +
                "\n" +
                "Nombre completo\n" +
                "Dirección de correo electrónico\n" +
                "Número de teléfono\n" +
                "Dirección postal\n" +
                "Datos de Transacciones:\n" +
                "\n" +
                "Historial de compras y ventas\n" +
                "Detalles de pago (p. ej., números de tarjeta de crédito, cuenta de PayPal)\n" +
                "Dirección de envío\n" +
                "Datos Técnicos y de Uso:\n" +
                "\n" +
                "Dirección IP\n" +
                "Tipo de dispositivo y sistema operativo\n" +
                "Información de geolocalización (si está habilitada)\n" +
                "Historial de navegación dentro de la aplicación\n" +
                "Datos de Comunicación:\n" +
                "\n" +
                "Mensajes y correspondencia con el servicio de atención al cliente\n" +
                "Comentarios y opiniones dejados en la plataforma\n" +
                "4. Finalidades del Tratamiento\n" +
                "Provisión de Servicios: Utilizamos sus datos para crear y gestionar su cuenta, procesar sus transacciones y proporcionar soporte al cliente.\n" +
                "Mejoras del Servicio: Analizamos los datos para mejorar nuestra aplicación, personalizar su experiencia y desarrollar nuevas funcionalidades.\n" +
                "Marketing: Enviamos comunicaciones comerciales y promociones sobre nuestros productos y servicios, siempre que tengamos su consentimiento.\n" +
                "Seguridad: Usamos sus datos para prevenir fraudes, realizar verificaciones de identidad y proteger la seguridad de nuestra plataforma.\n" +
                "5. Base Legal para el Tratamiento\n" +
                "El tratamiento de sus datos se basa en:\n" +
                "\n" +
                "Ejecución de un contrato: Necesitamos sus datos para proporcionar los servicios que ha solicitado.\n" +
                "Consentimiento: Para ciertos tipos de marketing y uso de datos de ubicación.\n" +
                "Cumplimiento de obligaciones legales: Retenemos y compartimos datos según lo requerido por la ley.\n" +
                "Intereses legítimos: Para mejorar nuestros servicios y prevenir fraudes.\n" +
                "6. Compartición de Datos\n" +
                "Podemos compartir sus datos personales con:\n" +
                "\n" +
                "Proveedores de servicios: Empresas que nos ayudan con procesamiento de pagos, envíos, marketing y soporte al cliente.\n" +
                "Autoridades legales: Cuando sea necesario para cumplir con la ley, regulaciones o procedimientos legales.\n" +
                "Compradores o vendedores: Datos limitados (como su nombre y detalles de contacto) para facilitar las transacciones entre usuarios.\n" +
                "7. Transferencias Internacionales\n" +
                "Podemos transferir sus datos personales a países fuera del Espacio Económico Europeo (EEE) que pueden no tener leyes de protección de datos equivalentes. En tales casos, garantizamos que se adopten las medidas adecuadas para proteger su información, como el uso de cláusulas contractuales estándar aprobadas por la Comisión Europea.\n" +
                "\n" +
                "8. Retención de Datos\n" +
                "Retenemos sus datos personales solo durante el tiempo necesario para cumplir con las finalidades para las que fueron recopilados, incluyendo cumplir con requisitos legales, contables o de informes. Por lo general, esto significa que conservaremos sus datos mientras tenga una cuenta con nosotros y durante un período después para cumplir con nuestras obligaciones legales.\n" +
                "\n" +
                "9. Derechos del Usuario\n" +
                "Usted tiene derecho a:\n" +
                "\n" +
                "Acceder: Solicitar una copia de los datos personales que tenemos sobre usted.\n" +
                "Rectificar: Solicitar la corrección de datos inexactos o incompletos.\n" +
                "Eliminar: Solicitar la eliminación de sus datos personales cuando ya no sean necesarios.\n" +
                "Oponerse: Oponerse al tratamiento de sus datos personales por motivos específicos.\n" +
                "Portabilidad: Solicitar la transferencia de sus datos a otro proveedor de servicios.\n" +
                "Para ejercer estos derechos, puede contactarnos en [Correo Electrónico de Contacto].\n" +
                "\n" +
                "10. Seguridad de los Datos\n" +
                "Implementamos medidas técnicas y organizativas adecuadas para proteger sus datos personales contra pérdida, uso indebido, acceso no autorizado, divulgación, alteración y destrucción. Esto incluye el uso de tecnologías de cifrado y autenticación.\n" +
                "\n" +
                "11. Cambios a esta Política de Privacidad\n" +
                "Podemos actualizar esta política de privacidad de vez en cuando. Le notificaremos sobre cualquier cambio publicando la nueva política en nuestra aplicación y, en caso de cambios significativos, a través de un aviso en nuestra plataforma o por correo electrónico.\n" +
                "\n" +
                "12. Contacto\n" +
                "Si tiene alguna pregunta o inquietud sobre esta política de privacidad o nuestras prácticas de protección de datos, puede contactarnos en [Correo Electrónico de Contacto] o por correo postal a [Dirección Completa].\n" +
                "\n" +
                "Fecha de última actualización: [Fecha]\n" +
                "\n" +
                "Este es un ejemplo general y debe adaptarse a las leyes y regulaciones específicas aplicables a la empresa y la ubicación de operación. También es recomendable consultar con un experto legal para garantizar que la política cumpla con todas las leyes pertinentes de protección de datos, como el RGPD en la UE o la CCPA en California.",
            style = MaterialTheme.typography.bodyLarge)
    }
}