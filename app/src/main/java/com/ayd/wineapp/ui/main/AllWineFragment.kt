package com.ayd.wineapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayd.wineapp.adapters.WineAdapter
import com.ayd.wineapp.databinding.FragmentAllWineBinding
import com.ayd.wineapp.model.Wine
import com.ayd.wineapp.utils.NetworkResult
import com.ayd.wineapp.viewmodels.MainViewModel


class AllWineFragment : Fragment() {

    private var _binding: FragmentAllWineBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { WineAdapter() }

    private lateinit var mainViewModel: MainViewModel

    private val args by navArgs<DetailWineFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAllWineBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setupRecyclerView()

        requestApiData()

        binding.root.setOnClickListener {
            val action = AllWineFragmentDirections.actionAllWineFragmentToDetailWineFragment(args.detail)
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setupRecyclerView(){
        binding.allRecyclerView.adapter = mAdapter
        binding.allRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        //binding.allRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
    }

    private fun requestApiData(){

        mainViewModel.getRedWine(applyQueries())
        mainViewModel.getWhiteWine(applyQueries())
        mainViewModel.getSparklingWine(applyQueries())
        mainViewModel.getRoseWine(applyQueries())

        mainViewModel.wineResponse.observe(viewLifecycleOwner){response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { mAdapter.setData(it)

                        val searchView = binding.wineSearchView
                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String): Boolean {
                                // Filter the data based on the submitted query here
                                val filteredData = response.data.filter { it.wine!!.lowercase().contains(query) }
                                mAdapter.setDataSearch(filteredData)

                                return false
                            }

                            override fun onQueryTextChange(newText: String): Boolean {
                                val filteredData = response.data.filter { it.wine!!.lowercase().contains(newText) }
                                mAdapter.setDataSearch(filteredData)
                                //mAdapter.notifyDataSetChanged()

                                return false
                            }
                        })

                    }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}