package com.example.proyectomovie_api.data.inicioSesion


// Este body es necesario para validar el token con el username y contraseña de la cuenta de movie database
data class BodyLogin(
    var username:String,
    val password:String,
    val request_token:String
)
