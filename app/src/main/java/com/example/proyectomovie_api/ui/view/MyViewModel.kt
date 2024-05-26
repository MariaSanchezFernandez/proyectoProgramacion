package com.example.proyectomovie_api.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomovie_api.data.Repository
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.inicioSesion.BodySessionID
import com.example.proyectomovie_api.data.inicioSesion.CreateGuestSessionResponse
import com.example.proyectomovie_api.data.inicioSesion.CreateSessionResponse
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


    fun getAuthToken() : MutableLiveData<String>{

        viewModelScope.launch {
            val response = repositorio.getAuthToken()

            if (response.code() == 200){
                response.body()?.request_token?.let {
                    requestToken.postValue(it)
                }
            }
        }
        return requestToken
    }


    fun createSessionLogin(body:BodyLogin) : MutableLiveData<RequestTokenResponse>{
        val liveData = MutableLiveData<RequestTokenResponse>()

        viewModelScope.launch {
            val response = repositorio.createSessionLogin(body)

            if (response.body()?.success == true){
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }

    fun getSessionID() = sessionID

    fun setSessionID(id:String){
        sessionID.value = id
    }

    fun createGuestSession() : MutableLiveData<CreateGuestSessionResponse>{
        val liveData = MutableLiveData<CreateGuestSessionResponse>()

        viewModelScope.launch {
            val response = repositorio.createGuestSession ()

            if (response.code() == 200){
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }

    fun createSession(bodySessionID: BodySessionID): MutableLiveData<CreateSessionResponse>{
        val liveData = MutableLiveData<CreateSessionResponse>()

        viewModelScope.launch {
            val response = repositorio.createSession(bodySessionID)

            if (response.code() == 200){
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }

}