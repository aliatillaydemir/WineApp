package com.ayd.wineapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayd.wineapp.R
import com.ayd.wineapp.adapters.WineAdapter
import com.ayd.wineapp.databinding.FragmentMainWineBinding
import com.ayd.wineapp.utils.NetworkResult
import com.ayd.wineapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainWineFragment : Fragment() {

    private var _binding: FragmentMainWineBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { WineAdapter() }

    private lateinit var mainViewModel: MainViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentMainWineBinding.inflate(inflater, container, false)
        //binding.lifecycleOwner = this
        //binding.mainViewModel = mainViewModel

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        setupRecyclerView()
        requestApiData()

        return binding.root
    }

    private fun setupRecyclerView(){
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,false)
    }

    private fun requestApiData(){
        Log.d("ProductDb","read api")
        mainViewModel.getWine(applyQueries())
        mainViewModel.wineResponse.observe(viewLifecycleOwner){response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }

    private fun applyQueries(): HashMap<String,String>{
        val queries: HashMap<String, String> = HashMap()

        return queries
    }


}