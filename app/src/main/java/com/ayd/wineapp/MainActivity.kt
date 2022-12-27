package com.ayd.wineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ayd.wineapp.databinding.ActivityMainBinding
import com.ayd.wineapp.ui.splash.SplashFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private lateinit var navController: NavController

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
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

        binding.bottomNavigationView.visibility = View.GONE


        launch {
            delay(4050)
            withContext(Dispatchers.Main){
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}