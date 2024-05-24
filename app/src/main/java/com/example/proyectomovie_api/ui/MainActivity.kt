package com.example.proyectomovie_api.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.databinding.MenuMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MenuMainBinding

    //Configura las barras
    private lateinit var appBarConfiguration: AppBarConfiguration

    //Controlador de navegación
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar2)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHost
        navController = navHost.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentInicio,
                R.id.fragmentPeliculas,
                R.id.fragmentSeries,
                R.id.fragmentCuenta
            ),binding.DrawerLayourFrame
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.drawerLayout.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}