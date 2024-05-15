package com.example.proyectomovie_api.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.databinding.ActivityLoginBinding



class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding



    val apiKey:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val usuario:String = binding.etUsernameLogin.text.toString()
            val password:String = binding.etPasswordLogin.text.toString()

            if (usuario.isNotEmpty() && password.isNotEmpty()){

                val token = viewModel.getAuthToken(apiKey).toString()
                if ( token.isNotEmpty() ) {

                    /* ME FALTA ABRIR EL HTTPS PARA AUTENTICAR EL REQUEST TOKEN
                    Y ASÍ PODER USARLO EN EL INICIO DE SESIÓN */

                    val body = BodyLogin(usuario, password, token)

                    val sessionID = viewModel.createSession(apiKey, body).toString()

                    if (sessionID.isNotEmpty()){
                        findNavController().navigate(R.id.)
                    }
                }
            }
        }
    }
}