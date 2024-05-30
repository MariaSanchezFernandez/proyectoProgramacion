package com.example.proyectomovie_api.ui.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.databinding.FragmentCuentaBinding
import com.example.proyectomovie_api.ui.LoginActivity
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel

class Cuenta : Fragment() {

    private lateinit var binding: FragmentCuentaBinding
    private val viewModel by activityViewModels<MyViewModel>()



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



        /// ESTE SESSION ID NO ME RECOJE LA DATA EN EL VIEWMODEL, NO SE PORQUE
        // -----------------------------------------------------------------------------
        viewModel.getSessionID().observe(viewLifecycleOwner){sessionID ->
            viewModel.getAccountDetails(sessionID.toString()).observe(viewLifecycleOwner) {

                if (it.avatar.tmdb.avatar_path?.isEmpty() == true) {
                    val imgURL = "https://secure.gravatar.com/avatar/${it.avatar.gravatar.hash}"
                    Glide.with(requireContext()).load(imgURL).into(binding.imageViewUSerAvatar)
                } else {
                    val imgURL = "https://image.tmdb.org/t/p/w200${it.avatar.tmdb.avatar_path}"
                    Glide.with(requireContext()).load(imgURL).into(binding.imageViewUSerAvatar)
                }

                binding.cuentaUsername.text = it.username

                if (it.name.length > 4) {
                    binding.textViewnombreUser.text = it.name
                } else {
                    binding.textViewnombreUser.visibility = View.GONE
                }

            }


            binding.buttonCerrarSesion.setOnClickListener {

                val builder = AlertDialog.Builder(requireContext())
                val viewDialog = layoutInflater.inflate(R.layout.alert_dialog_cerrar_sesion, null)
                builder.setView(viewDialog)

                val dialog = builder.create()
                dialog.window?.setDimAmount(0.7f)
                dialog.show()


                viewDialog.findViewById<Button>(R.id.bCancelAlertDialog).setOnClickListener {
                    dialog.dismiss()
                }

                viewDialog.findViewById<Button>(R.id.bCerrarSessionAlertDialog).setOnClickListener {
                    viewModel.deleteSession(sessionID.toString()).observe(viewLifecycleOwner) { success ->
                        if (success) {
                            val intentCerrarSesion = Intent(requireContext(), LoginActivity::class.java)
                            startActivity(intentCerrarSesion)
                            requireActivity().finishAffinity()
                        }
                        dialog.dismiss()
                    }
                }
            }
        }
    }
}