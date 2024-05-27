package com.example.proyectomovie_api.data.inicioSesion

data class RequestTokenResponse(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)