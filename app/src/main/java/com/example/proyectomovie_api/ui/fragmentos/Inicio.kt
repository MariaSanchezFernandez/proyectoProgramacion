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
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.databinding.AlertdialogBucadorBinding
import com.example.proyectomovie_api.databinding.FragmentInicioBinding
import com.example.proyectomovie_api.ui.MainActivity
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "Inicio"

            myViewModel.getInfoMovie().observe(viewLifecycleOwner){

                val topMovie = it[0]

                val baseUrl = "https://image.tmdb.org/t/p/original"

                binding.tTituloDia.text = topMovie.title
                binding.tFechaDia.text = topMovie.release_date
                Glide.with(requireContext()).load(baseUrl + topMovie.poster_path).into(binding.ivCarteleraDia)

            }

            myViewModel.getInfoPeople().observe(viewLifecycleOwner){people ->
                val topActor = people[0]

                val baseUrl = "https://image.tmdb.org/t/p/original"

                binding.tvNameActor.text = topActor.name
                binding.tvParticipated.text = topActor.known_for.get(0).title
                Glide.with(requireContext()).load(baseUrl + topActor.profile_path).into(binding.ivActor)
            }



            navController = findNavController()

            binding.floatingActionButton.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                val viewBinding = layoutInflater.inflate(R.layout.alertdialog_bucador, null)
                builder.setView(viewBinding)
                val dialog = builder.create()

                viewBinding.findViewById<Button>(R.id.button2).setOnClickListener {
                    val name = viewBinding.findViewById<EditText>(R.id.textInputEditText).text.toString()
                    //val intent = Intent(requireContext(), Inicio::class.java)
                    //intent.putExtra("informacion", name)

                    //Bundle bundle = new Bundle()

                    //myViewModel.getMovi(name)
                    myViewModel.getPersonBuscador(name)

                    navController.navigate(R.id.action_fragmentInicio_to_buscadorLista)

                   // startActivity(intent)
                    dialog.dismiss()
                }
                dialog.show()
            }

            val observer = Observer<Movie>{
//                configRecycler(it)
            }
    }

    private fun configRecycler(list: List<Movie>){

    }
}