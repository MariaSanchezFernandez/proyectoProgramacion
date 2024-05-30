package com.example.proyectomovie_api.ui.fragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.proyectomovie_api.data.people.People
import com.example.proyectomovie_api.databinding.FragmentBuscadorListaBinding
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorBuscadorPeliculas
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorBuscadorPersonas
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorBuscadorSeries
import com.example.proyectomovie_api.ui.view.MyViewModel


class BuscadorLista : Fragment() {

    private lateinit var binding: FragmentBuscadorListaBinding
    private lateinit var adapterSeries : AdaptadorBuscadorSeries
    private lateinit var adapterPeliculas: AdaptadorBuscadorPeliculas
    private lateinit var adapterPersonas: AdaptadorBuscadorPersonas
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



//        myViewModel.getBuscadorActores().observe(viewLifecycleOwner){
//            val intent = Intent(Intent.ACTION_SENDTO)
//            intent.type = "text/plain"
//            val informacion = intent.extras?.getString("informacion")
//            intent.putExtra(Intent.EXTRA_TEXT, informacion)
//            startActivity(intent)
////            configRecycler(it)
//        }
    }



//    private fun configRecycler(myViewModel: MyViewModel){
//
//        val recyclerView = binding.recycler
//        adapterPersonas = AdaptadorBuscadorPersonas(myViewModel, object : AdaptadorBuscadorPersonas.MiClick{
//            override fun onHolderClick(person: People) {
//                myViewModel.getBuscadorActores()
//
//
//            }
//
//        })
//
//    }

}