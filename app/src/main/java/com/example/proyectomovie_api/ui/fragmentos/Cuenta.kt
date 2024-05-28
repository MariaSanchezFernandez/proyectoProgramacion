package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.inicioSesion.BodySessionID
import com.example.proyectomovie_api.databinding.FragmentCuentaBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel

class Cuenta : Fragment() {

    private lateinit var binding: FragmentCuentaBinding
    private val viewModel by activityViewModels<MyViewModel>()

    private val sessionID = viewModel.getSessionID()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentCuentaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "Cuenta"


        viewModel.getAccountDetails(sessionID.toString()).observe(viewLifecycleOwner){


            if (it.avatar.tmdb.avatar_path.isEmpty()){
                val imgURL = "https://secure.gravatar.com/avatar/${it.avatar.gravatar.hash}"
                Glide.with(requireContext()).load(imgURL).into(binding.imageViewUSerAvatar)
            }else{
                val imgURL= "https://image.tmdb.org/t/p/w200${it.avatar.tmdb.avatar_path}"
                Glide.with(requireContext()).load(imgURL).into(binding.imageViewUSerAvatar)
            }

            binding.cuentaUsername.text = it.username

            if (it.name.length>4){
                binding.textViewnombreUser.text = it.name
            }else{
                binding.textViewnombreUser.visibility = View.GONE
            }








            binding.buttonCerrarSesion.setOnClickListener{

            }







        }





    }
}