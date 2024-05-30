package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.people.People
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.FragmentBuscadorListaBinding
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorBuscadorPeliculas
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorBuscadorSeries
import com.example.proyectomovie_api.ui.view.MyViewModel


class BuscadorLista : Fragment() {

    private lateinit var binding: FragmentBuscadorListaBinding
    private val myViewModel by activityViewModels<MyViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBuscadorListaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.getBuscadorPeliculas().observe(viewLifecycleOwner){
            configRecylerMovie(it)
        }

/*        myViewModel.getBuscadorSeries().observe(viewLifecycleOwner){
            configRecylerSeries(it)
        }*/
    }

    private fun configRecylerMovie(list:List<Movie>){
        val recyclerView = binding.recycler
         val adapterMovie = AdaptadorBuscadorPeliculas(list, object :AdaptadorBuscadorPeliculas.MiClick{
             override fun onHolderClick(pelicula: Movie) {
                 val id = pelicula.id
                 myViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner) {
                     if (it != null) {
                         myViewModel.setPelicula(it)
                         findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                     }
                 }

             }

             })

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterMovie
         }

    private fun configRecylerSeries(list:List<TVShow>){
        val recyclerView = binding.recycler
        val adapterMovie = AdaptadorBuscadorSeries(list, object : AdaptadorBuscadorSeries.MiClick{
            override fun onHolcerClick(show: TVShow) {
                val id = show.id
                myViewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){
                    if (it != null){
                        myViewModel.setSerie(it)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }


        })

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterMovie
    }


    }




