package com.ayd.wineapp.ui.onboarding.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayd.wineapp.R
import com.ayd.wineapp.databinding.FragmentViewPagerBinding
import com.ayd.wineapp.ui.onboarding.FirstOnBoard
import com.ayd.wineapp.ui.onboarding.SecondOnBoard
import com.ayd.wineapp.ui.onboarding.ThirdOnBoard


class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstOnBoard(),
            SecondOnBoard(),
            ThirdOnBoard()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}