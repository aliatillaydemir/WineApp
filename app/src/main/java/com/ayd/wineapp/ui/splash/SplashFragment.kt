package com.ayd.wineapp.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.wineapp.MainActivity
import com.ayd.wineapp.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class SplashFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        launch {
            delay(4000)
            withContext(coroutineContext){
                if(onBoardingFinished()){
                    startActivity(Intent(activity,MainActivity::class.java))
                    activity?.onBackPressed()
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
                }
            }
        }

    }


    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("ONBOARD", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("FINISHED", false)
    }


}