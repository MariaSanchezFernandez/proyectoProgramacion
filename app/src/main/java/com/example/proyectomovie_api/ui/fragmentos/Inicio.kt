package com.example.proyectomovie_api.ui.fragmentos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.databinding.AlertdialogBucadorBinding
import com.example.proyectomovie_api.databinding.FragmentInicioBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaPeliculas
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.google.android.material.search.SearchView

class Inicio : Fragment() {


    private lateinit var binding: FragmentInicioBinding
    private val myViewModel by activityViewModels<MyViewModel>()
    private lateinit var navController :NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "Inicio"


        //Recoge la información que necesitamos de la pelicula, titulo, fecha y poster y lo muestra
            myViewModel.getInfoMovie().observe(viewLifecycleOwner){movieList->

                val topMovie = movieList[0]

                val baseUrl = "https://image.tmdb.org/t/p/original"

                binding.tTituloDia.text = topMovie.title
                binding.tFechaDia.text = topMovie.release_date
                Glide.with(requireContext()).load(baseUrl + topMovie.poster_path).into(binding.ivCarteleraDia)

            }

        //Recoge la información que necesitamos del actor/actriz, nombre, 1 pelicula donde ha participado y la foto del mismo
            myViewModel.getInfoPeople().observe(viewLifecycleOwner){people ->
                val topActor = people[0]

                val baseUrl = "https://image.tmdb.org/t/p/original"

                binding.tvNameActor.text = topActor.name
                binding.tvParticipated.text = topActor.known_for.get(0).title
                Glide.with(requireContext()).load(baseUrl + topActor.profile_path).into(binding.ivActor)
            }



            navController = findNavController()


        val sesionId = myViewModel.getSessionID()
        val acountId = myViewModel.getAccountID(sesionId.toString())

        acountId.value?.let {myViewModel.getFavoriteMovies(it)}?.observe(viewLifecycleOwner){
            configRecyclerMovies(it)
        }



    }


    //Recoge la lista de peliculas que añades a favoritos y las muestra en un recycler de forma linearLayout
    private fun configRecyclerMovies(lista : List<Movie>){
        myViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            myViewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){listaFavoritos ->
                val adaptadorFavoritos = AdaptadorMiListaPeliculas(lista as ArrayList<Movie>, object : AdaptadorMiListaPeliculas.MyClick{
                    override fun onHolderClick(pelicula: Movie) {
                        val id = pelicula.id
                        myViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                            if (it !== null){
                                myViewModel.setPelicula(it)
                                findNavController().navigate(R.id.action_favoritos_to_informacion)
                            }
                        }
                    }

                    override fun onItemLongClick(pelicula: Movie) {

                    }

                })

                binding.recyclerViewPeliculasTuLista.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.recyclerViewPeliculasTuLista.adapter = adaptadorFavoritos

            }

        }
    }


}