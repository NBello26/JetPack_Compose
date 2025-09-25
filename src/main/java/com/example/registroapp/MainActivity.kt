package com.example.registroapp

import android.os.Bundle // Manejo del ciclo de vida
import androidx.activity.ComponentActivity // Actividad principal
import androidx.activity.compose.setContent // Para definir UI con Compose
import androidx.compose.foundation.Image // Para mostrar imágenes
import androidx.compose.foundation.background // Para aplicar color de fondo
import androidx.compose.foundation.layout.* // Column, Spacer, etc.
import androidx.compose.material3.* // Componentes Material 3
import androidx.compose.runtime.* // Estados
import androidx.compose.ui.Alignment // Alineación
import androidx.compose.ui.Modifier // Modificadores para diseño
import androidx.compose.ui.graphics.Color // Colores personalizados
import androidx.compose.ui.res.painterResource // Para cargar imágenes
import androidx.compose.ui.text.input.PasswordVisualTransformation // Ocultar contraseña
import androidx.compose.ui.tooling.preview.Preview // Vista previa
import androidx.compose.ui.unit.dp // Unidades de medida
import androidx.compose.ui.unit.sp // Tamaño de texto
import com.example.registroapp.ui.theme.RegistroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroAppTheme {
                LoginScreen() // Llamamos a la pantalla de login
            }
        }
    }
}
@Composable//Cada vez que hay un composable estoy creando un trozo de UI
fun LoginScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .background(Color(0xFFEDF2F7)) // Fondo gris claro
            .padding(24.dp), // Margen interno general
        horizontalAlignment = Alignment.CenterHorizontally, // Centrado horizontal
        verticalArrangement = Arrangement.Center // Centrado vertical
    ) {
        // ---------- LOGO ----------
        Image(
            painter = painterResource(id = R.drawable.logo_duoc), // Carga el logo
            contentDescription = "Logo", // Descripción accesible
            modifier = Modifier
                .height(100.dp) // Alto del logo
                .padding(bottom = 32.dp) // Espacio debajo del logo
        )

    //Estados o datos a capturar

    var nombre by remember {mutableStateOf("")}//Guardar el nombre del usuario
    var email by remember { mutableStateOf("") }//correo
    var contrasena by remember { mutableStateOf("") }//contraseña
    var newcontrasena by remember { mutableStateOf("") } //confirmacion contraseña
    var sede by remember { mutableStateOf("") } //sede
    var mensaje by remember { mutableStateOf("") }//Mensaje

    //Estructura principal de la aplicación
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),//Utilice la pantalla completa y agregue un padding
        horizontalAlignment = Alignment.CenterHorizontally,// Centrando horizontalmente
        verticalArrangement = Arrangement.Center///
    ){
        //Campo de nombre = TextField
        TextField(
            value = nombre, //lo relaciono con la variable
            onValueChange = {nombre = it}, //it = texto que cambia cuando el user lo escribe
            label = {Text("Ingresa tu nombre")}
        )
        //Barra de espacio
        Spacer(modifier= Modifier.height(12.dp))//dp = unidad de medida

        //Campo de correo

        TextField(
            value = email,
            onValueChange = { email = it},
            label = {Text("Ingresa tu correo")}
        )
        //Barra de espacio
        Spacer(modifier= Modifier.height(12.dp))//dp = unidad de medida

        //Campo de Contraseña
        TextField(
            value = contrasena,
            onValueChange = { contrasena = it},
            label = {Text("Ingresa tu contraseña")},
            visualTransformation = PasswordVisualTransformation()
        )
        //Barra de espacio
        Spacer(modifier= Modifier.height(12.dp))//dp = unidad de medida


        //Campo de confirmacion de contraseña
        TextField(
            value = newcontrasena,
            onValueChange = { newcontrasena = it},
            label = {Text("Confirma tu contraseña")},
            visualTransformation = PasswordVisualTransformation()
        )
        //Barra de espacio
        Spacer(modifier= Modifier.height(12.dp))//dp = unidad de medida

        //Campo de confirmacion de contraseña
        TextField(
            value = sede,
            onValueChange = { sede = it},
            label = {Text("Confirma tu sede:")}
        )
        //Barra de espacio
        Spacer(modifier= Modifier.height(12.dp))//dp = unidad de medida

        //Botón del formulario
        Button(onClick = {
            if (nombre.isNotEmpty()){
                if (email.isNotEmpty()){
                    if ( "@gmail.com" in email.lowercase()|| "@duocuc.cl" in email.lowercase() || "@profesorduoc.cl" in email.lowercase()){
                        if (contrasena.isNotEmpty()){
                            if (newcontrasena.isNotEmpty()){
                                if (contrasena == newcontrasena){
                                    if (sede.isNotEmpty()){
                                        mensaje = "Hola, $nombre. \nCorreo: ${email.lowercase()}, \nSede: $sede."
                                    }else{
                                        mensaje = "Falta el campo de sede, por favor ingresela."
                                    }
                                }else{
                                    mensaje = "Las contraseñas no coinciden."
                                }
                            }else{
                                mensaje = "Falta el campo de confirmar contraseña, por favor ingresela."
                            }
                        }else{
                            mensaje = "Falta el campo de su contraseña, por favor ingresela."
                        }
                    }else{
                        mensaje = "Ingrese un correo valido, @gmail.com, @duocuc.cl o @profesorduoc.cl"
                    }
                }else{
                    mensaje = "Falta el campo de su correo, por favor ingreselo."
                }
            }else{
                mensaje = "Falta el campo de su nombre, por favor ingreselo."
            }
        },
                colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC107),
                contentColor = Color(0XFF000000),
        )

        ) {

            Text("Registrar")//Texto del botón
        }
        Spacer(modifier= Modifier.height(12.dp))//dp = unidad de medida

        //Texto sea dinámico y aparezca después de presionar el botón
        if(mensaje.isNotEmpty()){
            Text(
                text = mensaje,
                style = MaterialTheme.typography.headlineSmall //un tamaño de letra pequeña
            )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    RegistroAppTheme {
        LoginScreen()
    }
}