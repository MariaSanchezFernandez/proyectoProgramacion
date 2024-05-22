package com.example.proyectomovie_api.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.databinding.ActivityLoginBinding
import com.example.proyectomovie_api.ui.view.MyViewModel


class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<MyViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // BOTON DE LOGIN

        binding.buttonLogin.setOnClickListener {

            val usuario = binding.etUsernameLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()
            var authToken = ""
            var sessionID = ""


            // COMPRUEBA QUE HAYA USUARIO Y CONTRASEÑA
            if (usuario.isNotEmpty() && password.isNotEmpty()){

                viewModel.getAuthToken().observe(this, Observer { value ->
                    authToken = value
                })
                if ( authToken.length > 1 ) {

                    // TE ENVIA A LA URL PARA VALIDAR EL TOKEN
                    val pageURL = "https://www.themoviedb.org/authenticate/${authToken}"
                    val intentValidateToken = Intent(Intent.ACTION_VIEW)
                    intentValidateToken.data = Uri.parse(pageURL)
                    try {
                        startActivity(intentValidateToken)
                    } catch (_: ActivityNotFoundException) { }

                    // UNA VEZ VALIDADO, SE CREA UN BODYLOGIN CON EL USUARIO, CONTRASEÑA Y TOKEN
                    val body = BodyLogin(usuario, password, authToken)
                        viewModel.createSession(body).observe(this, Observer { value ->
                            sessionID = value
                        })

                    // COMPRUEBA EL ID DE SESIÓN Y LO ENVIA AL MAIN ACTIVITY PARA USARLO EN EL RESTO DE PETICIONES
                    if (sessionID.length > 1 ){
                         val intentCreateSessionID = Intent(this, MainActivity::class.java).apply{
                             putExtra("sessionID", sessionID)
                         }
                        startActivity(intentCreateSessionID)
                        finish()

                    }else{
                        Toast.makeText(this, "Error al iniciar sesión...", Toast.LENGTH_LONG ).show()
                    }
                }else{
                    Toast.makeText(this, "Error al generar token...", Toast.LENGTH_LONG ).show()
                }
            }else{
                Toast.makeText(this, "Debes introducir un usuario y una contraseña", Toast.LENGTH_LONG).show()
            }
        }

        // TEXTO CLICKABLE PARA ENTRAR COMO INVITADO

        binding.textViewGuest.setOnClickListener {

            var guestID = ""

            viewModel.createGuestSession().observe(this, Observer { value ->
                guestID = value
            })

            // COMPRUEBA EL ID DE SESIÓN Y LO ENVIA AL MAIN ACTIVITY PARA USARLO EN EL RESTO DE PETICIONES
            if (guestID.length>1){
                val intentCreateGuestSessionID = Intent(this, MainActivity::class.java).apply{
                    putExtra("sessionID", guestID)
                }
                startActivity(intentCreateGuestSessionID)
                finish()
            }
        }
    }
}