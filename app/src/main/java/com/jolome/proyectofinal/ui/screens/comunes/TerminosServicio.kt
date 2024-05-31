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
fun terminosServicio(navController: NavController){
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "1. Introducción\n" +
                    "Estos Términos de Servicio (\"Términos\") rigen el uso de [Nombre de la Aplicación] (la \"Aplicación\") operada por [Nombre de la Empresa] (\"nosotros\" o \"nuestro\"). Al acceder o utilizar nuestra Aplicación, usted (\"usuario\") acepta estar sujeto a estos Términos.\n" +
                    "\n" +
                    "2. Registro y Cuenta\n" +
                    "Elegibilidad: Para usar nuestra Aplicación, debe tener al menos 18 años o tener el permiso de un tutor legal.\n" +
                    "Registro: Debe proporcionar información precisa y completa al registrarse y mantener actualizados sus datos.\n" +
                    "Seguridad de la Cuenta: Usted es responsable de mantener la confidencialidad de sus credenciales de inicio de sesión y de todas las actividades que ocurran bajo su cuenta.\n" +
                    "3. Uso de la Aplicación\n" +
                    "Uso Permitido: Usted puede usar la Aplicación para comprar y vender artículos de segunda mano, conforme a estos Términos y a las leyes aplicables.\n" +
                    "Restricciones: No debe usar la Aplicación para ningún propósito ilegal, engañoso, malicioso o discriminatorio. Está prohibido publicar contenido que infrinja derechos de terceros, incluyendo derechos de autor, marcas comerciales y otros derechos de propiedad intelectual.\n" +
                    "4. Contenido del Usuario\n" +
                    "Propiedad del Contenido: Usted conserva todos los derechos sobre el contenido que publique en la Aplicación, pero otorga a [Nombre de la Empresa] una licencia no exclusiva, mundial, libre de regalías para usar, copiar, modificar y mostrar dicho contenido en relación con la operación de la Aplicación.\n" +
                    "Responsabilidad: Usted es responsable del contenido que publique y garantiza que tiene todos los derechos necesarios para publicar dicho contenido y que este no infringe ninguna ley ni derechos de terceros.\n" +
                    "5. Transacciones\n" +
                    "Roles de la Plataforma: [Nombre de la Empresa] no es parte de las transacciones entre compradores y vendedores. Proporcionamos la plataforma para que las transacciones puedan realizarse, pero no garantizamos la calidad, seguridad o legalidad de los artículos listados.\n" +
                    "Pagos: Todas las transacciones de pago se gestionan a través de terceros proveedores de servicios de pago. Usted acepta cumplir con los términos y condiciones de dichos proveedores.\n" +
                    "6. Resolución de Disputas\n" +
                    "Entre Usuarios: Los usuarios deben intentar resolver cualquier disputa directamente entre ellos. [Nombre de la Empresa] puede proporcionar asistencia limitada a su discreción.\n" +
                    "Con [Nombre de la Empresa]: Cualquier disputa entre usted y [Nombre de la Empresa] será resuelta mediante arbitraje vinculante, a menos que las leyes aplicables dispongan lo contrario.\n" +
                    "7. Terminación\n" +
                    "Terminación por el Usuario: Puede cerrar su cuenta en cualquier momento a través de las configuraciones de la cuenta.\n" +
                    "Terminación por [Nombre de la Empresa]: Podemos suspender o cerrar su cuenta y acceso a la Aplicación en cualquier momento, sin previo aviso, si creemos que ha violado estos Términos.\n" +
                    "8. Limitación de Responsabilidad\n" +
                    "[Nombre de la Empresa] no será responsable por cualquier daño indirecto, incidental, especial, consecuente o punitivo, ni por pérdida de ingresos, ganancias o datos, que surja del uso o la incapacidad de usar la Aplicación.\n" +
                    "9. Indemnización\n" +
                    "Usted acepta indemnizar y eximir de responsabilidad a [Nombre de la Empresa], sus directivos, empleados y agentes, de cualquier reclamación, pérdida, daño, responsabilidad, costos y gastos (incluidos los honorarios de abogados) derivados de su uso de la Aplicación o su violación de estos Términos.\n" +
                    "10. Cambios en los Términos\n" +
                    "Nos reservamos el derecho de modificar estos Términos en cualquier momento. Le notificaremos sobre cualquier cambio publicando los nuevos Términos en la Aplicación. Su uso continuado de la Aplicación después de la publicación de los cambios constituirá su aceptación de dichos cambios.\n" +
                    "11. Misceláneos\n" +
                    "Divisibilidad: Si alguna disposición de estos Términos es considerada inválida o inaplicable, las disposiciones restantes permanecerán en pleno vigor y efecto.\n" +
                    "Ley Aplicable: Estos Términos se regirán e interpretarán de acuerdo con las leyes de [Jurisdicción].\n" +
                    "Acuerdo Completo: Estos Términos, junto con nuestra Política de Privacidad, constituyen el acuerdo completo entre usted y [Nombre de la Empresa] en relación con el uso de la Aplicación.",
            style = MaterialTheme.typography.bodyLarge
        )

    }
}