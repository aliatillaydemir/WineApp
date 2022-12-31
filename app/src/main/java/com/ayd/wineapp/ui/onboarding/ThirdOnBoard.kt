package com.ayd.wineapp.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ayd.wineapp.MainActivity
import com.ayd.wineapp.R
import com.ayd.wineapp.databinding.FragmentThirdOnBoardBinding


class ThirdOnBoard : Fragment() {

    private var _binding: FragmentThirdOnBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentThirdOnBoardBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)  //view pager'a ulaşmak için findViewByid kullandım.

        binding.backtoSecondButton.setOnClickListener{
            viewPager?.currentItem = 1
        }
        binding.finishButton.setOnClickListener{
            startActivity(Intent(activity,MainActivity::class.java))
            onBoardFinish()
        }


        return binding.root
    }


    private fun onBoardFinish(){
        val sharedPref = requireActivity().getSharedPreferences("ONBOARD", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("FINISHED",true)
        editor.apply()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}