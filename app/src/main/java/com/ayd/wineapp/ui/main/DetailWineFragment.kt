package com.ayd.wineapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.ayd.wineapp.R
import com.ayd.wineapp.databinding.FragmentDetailWineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailWineFragment : Fragment() {

    private val args by navArgs<DetailWineFragmentArgs>()

    private var _binding: FragmentDetailWineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailWineBinding.inflate(inflater, container, false)

        binding.imageView6.load(args.detail.image)
        binding.textView16.text = args.detail.wine.toString()

        return binding.root
    }


}