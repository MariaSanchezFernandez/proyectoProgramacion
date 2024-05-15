package com.example.proyectomovie_api.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomovie_api.data.Repository
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.inicioSesion.RequestTokenResponse
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.tv.TVShow
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {


    private val repositorio = Repository()
    private val listaMovies = MutableLiveData<ArrayList<Movie>>()
    private val listaTVShows = MutableLiveData<ArrayList<TVShow>>()
    private val requestToken = MutableLiveData<String>()
    private val sessionID = MutableLiveData<String>()


    fun getAuthToken(apiKey:String) : MutableLiveData<String>{

        viewModelScope.launch {
            val response = repositorio.getAuthToken(apiKey)

            if (response.code() == 200){
                response.body()?.request_token.let {
                    requestToken.postValue(it)
                }
            }
        }
        return requestToken
    }


    fun createSession(apiKey: String, body:BodyLogin) : MutableLiveData<String>{

        viewModelScope.launch {
            val response = repositorio.createSessionLogin(apiKey, body)

            if (response.code() == 200){
                response.body()?.guest_session_id.let {
                    sessionID.postValue(it)
                }
            }
        }
        return sessionID
    }

    fun getSessionID() = sessionID





}