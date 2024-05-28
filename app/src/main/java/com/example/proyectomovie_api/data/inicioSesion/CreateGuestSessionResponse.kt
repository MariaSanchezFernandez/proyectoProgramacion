package com.example.proyectomovie_api.data.inicioSesion

data class CreateGuestSessionResponse(
    val expires_at: String,
    val guest_session_id: String,
    val success: Boolean
)