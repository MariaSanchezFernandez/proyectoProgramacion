package com.example.proyectomovie_api.data.inicioSesion

data class BodyLogin(
    var username:String,
    val password:String,
    val request_token:String
)
