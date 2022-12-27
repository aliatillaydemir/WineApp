package com.ayd.wineapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayd.wineapp.R
import com.ayd.wineapp.adapters.WineAdapter
import com.ayd.wineapp.databinding.FragmentMainBinding
import com.ayd.wineapp.utils.NetworkResult
import com.ayd.wineapp.viewmodels.MainViewModel


class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object{
        var wine = "?"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)


        binding.cardView1.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mainWineFragment)
            wine = "red"
        }

        binding.cardView2.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mainWineFragment)
            wine = "white"
        }

        binding.cardView3.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mainWineFragment)
            wine = "sparkling"
        }

        binding.cardView4.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mainWineFragment)
            wine = "rose"
        }

        return binding.root
    }





}