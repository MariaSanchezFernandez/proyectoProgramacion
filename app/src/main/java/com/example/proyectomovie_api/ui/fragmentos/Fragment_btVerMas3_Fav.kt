package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.proyectomovie_api.databinding.FragmentBtVerMas3FavBinding
import com.example.proyectomovie_api.ui.view.MyViewModel

class fragment_btVerMas3_Fav : Fragment() {

    private var _binding: FragmentBtVerMas3FavBinding? = null
    private val binding get() = _binding!!
    private val myViewModel by  activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBtVerMas3FavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.getFavMovies().observe(viewLifecycleOwner){}
        myViewModel.getFavTVShows().observe(viewLifecycleOwner){}

    }

}
