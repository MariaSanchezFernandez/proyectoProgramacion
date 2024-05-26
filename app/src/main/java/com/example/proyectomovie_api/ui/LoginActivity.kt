package com.example.proyectomovie_api.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.inicioSesion.BodySessionID
import com.example.proyectomovie_api.databinding.ActivityLoginBinding

import com.example.proyectomovie_api.ui.view.MyViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<MyViewModel>()

    private lateinit var username: String
    private lateinit var password: String
    private lateinit var authToken: String
    private lateinit var sessionID: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // BOTON PARA AUTH TOKEN

        binding.buttonAuthToken.setOnClickListener {

            username = binding.etUsernameLogin.text.toString()
            password = binding.etPasswordLogin.text.toString()

            // COMPRUEBA QUE HAYA USUARIO Y CONTRASEÑA
            if (username.isNotEmpty() && password.isNotEmpty()){

                viewModel.getAuthToken().observe(this){token ->
                    authToken = token
                    if ( authToken.length > 1 ) {

                        // TE ENVIA A LA URL PARA VALIDAR EL TOKEN
                        val pageURL = "https://www.themoviedb.org/authenticate/${authToken}"
                        val intentValidateToken = Intent(Intent.ACTION_VIEW)
                        intentValidateToken.data = Uri.parse(pageURL)
                        try {
                            startActivity(intentValidateToken)
                        } catch (_: ActivityNotFoundException) { }

                      }else{
                        Toast.makeText(this, "Error al generar token...", Toast.LENGTH_LONG ).show()
                    }
                }

            }else{
                Toast.makeText(this, "Debes introducir un usuario y una contraseña", Toast.LENGTH_LONG).show()
            }
            cambiaBotones()
        }

        // BOTON PARA INICIAR UNA SESION

        binding.buttonLogin.setOnClickListener {

            // UNA VEZ VALIDADO, SE CREA UN BODYLOGIN CON EL USUARIO, CONTRASEÑA Y TOKEN
            val bodyLogin  = BodyLogin(username, password, authToken)

            // EL BODY SE USA PARA VALIDAR UNA SESSION CON USUARIO Y CONTRASEÑA CON LA CUENTA DE MOVIEDATABASE
            viewModel.createSessionLogin(bodyLogin).observe(this){ sessionIDLogin ->
                // COMPRUEBA QUE LA VALIDACIÓN SEA CORRECTA Y VUELVE A GUARADAR EL TOKEN
                //  el token es el mismo que antes, por lo que volver a guardarlo es técnicamente innecesario
                if (sessionIDLogin.success){
                    authToken = sessionIDLogin.request_token


                    //CREA UN BODY CON EL TOKEN, PARA ESTA VEZ CREAR LA SESIÓN ID

                    val body = BodySessionID(authToken)
                    viewModel.createSession(body).observe(this){
                        // guardamos la session ID en viewmodel
                        viewModel.setSessionID(it.session_id)
                        sessionID = it.session_id
                        // Intent con la sessión id se manda al main activity
                        val intentCreateGuestSessionID = Intent(this, MainActivity::class.java).apply{
                            putExtra("sessionID", sessionID)
                        }
                        startActivity(intentCreateGuestSessionID)
                        finish()
                    }
                }else{
                    Toast.makeText(this, "Error al autenticar la sesión...", Toast.LENGTH_LONG ).show()
                }
            }
            cambiaBotones()
        }

        // TEXTO CLICKABLE PARA ENTRAR COMO INVITADO

        binding.textViewGuest.setOnClickListener {

            viewModel.createGuestSession().observe(this) {
                if (it.success){
                    sessionID = it.guest_session_id
                    val intentCreateGuestSessionID = Intent(this, MainActivity::class.java).apply{
                        putExtra("sessionID", sessionID)
                    }
                    startActivity(intentCreateGuestSessionID)
                    finish()
                }
            }
        }
    }


    // FUNCIÓN SIMPLE QUE CAMBIA AMBOS BOTONES, DE FORMA QUE EL USUARIO LO PERCIBA COMO UN ÚNICO BOTÓN
    fun cambiaBotones(){
        if (binding.buttonAuthToken.isVisible) {
            binding.buttonAuthToken.visibility = View.GONE
            binding.buttonLogin.visibility = View.VISIBLE
        }else{
            binding.buttonAuthToken.visibility = View.VISIBLE
            binding.buttonLogin.visibility = View.GONE
        }
    }

}