package com.example.proyectomovie_api.ui.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.databinding.FragmentCuentaBinding
import com.example.proyectomovie_api.ui.LoginActivity
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.util.Locale

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

        viewModel.getUserType().observe(viewLifecycleOwner){userType ->
            if (userType == "Usuario"){
                viewModel.getSessionID().observe(viewLifecycleOwner){sessionID ->
                    viewModel.getAccountDetails(sessionID).observe(viewLifecycleOwner){accountDetails ->

                        if (accountDetails.avatar.tmdb.avatar_path.isNullOrBlank()){
                            val imgURL = "https://secure.gravatar.com/avatar/${accountDetails.avatar.gravatar.hash}"
                            Glide.with(requireActivity()).load(imgURL).into(binding.imageViewUSerAvatar)
                        }else{
                            val imgURL= "https://image.tmdb.org/t/p/w200${accountDetails.avatar.tmdb.avatar_path}"
                            Glide.with(requireActivity())
                                .load(imgURL)
                                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(45, 0)))
                                .into(binding.imageViewUSerAvatar)
                        }

                        binding.cuentaUsername.text = accountDetails.username

                        if (accountDetails.name.length>4){
                            binding.textViewnombreUser.text =accountDetails.name
                        }else{
                            binding.textViewnombreUser.visibility = View.GONE
                        }

                        val targetLocale = Locale(accountDetails.iso_639_1,accountDetails.iso_3166_1)
                        val languageLocale = Locale(accountDetails.iso_639_1)
                        val countryLocale = Locale("",accountDetails.iso_3166_1)

                        val languageName = languageLocale.getDisplayLanguage(targetLocale)
                        val countryName = countryLocale.getDisplayCountry(targetLocale)


                        binding.textViewIdioma.text = languageName
                        binding.textViewNacionalidad.text = countryName
                    }
                }
            }else{
                binding.imageViewUSerAvatar.visibility = View.GONE
                binding.textViewIdioma.visibility = View.GONE
                binding.textViewNacionalidad.visibility = View.GONE
                binding.textViewTIdioma.visibility = View.GONE
                binding.textViewtNacionalidad.visibility = View.GONE
                binding.textViewnombreUser.visibility = View.GONE
                binding.cuentaUsername.text = userType

                binding.textViewInvitado.visibility = View.VISIBLE
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
                    if (userType == "Usuario"){
                        viewModel.getSessionID().observe(viewLifecycleOwner){sessionID ->
                            viewModel.deleteSession(sessionID.toString()).observe(viewLifecycleOwner) { success ->
                                if (success) {
                                    val intentCerrarSesion = Intent(requireContext(), LoginActivity::class.java)
                                    startActivity(intentCerrarSesion)
                                    requireActivity().finishAffinity()
                                }
                                dialog.dismiss()
                            }
                        }
                    }else{
                        val intentCerrarSesion = Intent(requireContext(), LoginActivity::class.java)
                        startActivity(intentCerrarSesion)
                        requireActivity().finishAffinity()
                        dialog.dismiss()
                    }
                }
            }
        }
    }
}