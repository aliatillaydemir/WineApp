package com.ayd.wineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ayd.wineapp.databinding.ActivityMainBinding
import com.ayd.wineapp.ui.splash.SplashFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_WineApp)
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val navHostFragment = supportFragmentManager.findFragmentById(
           R.id.fragmentContainerView
       ) as NavHostFragment

       navController = navHostFragment.navController

       val appBarConfiguration = AppBarConfiguration(
           setOf(
               R.id.mainFragment,
               R.id.allWineFragment,
               R.id.detailFragment
           )
       )

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val parentView = bottomNav.parent as? ViewGroup

        navController.addOnDestinationChangedListener { _, destination, _ ->

            if (destination.id == R.id.mainWineFragment) {
                //parentView?.removeView(bottomNav)
                bottomNav?.visibility = View.GONE
            }
            else{
                //parentView?.removeView(bottomNav)
                //parentView?.addView(bottomNav)
                bottomNav?.visibility = View.VISIBLE
            }

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}