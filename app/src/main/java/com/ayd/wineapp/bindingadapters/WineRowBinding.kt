package com.ayd.wineapp.bindingadapters

import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.ayd.wineapp.model.WineItem
import com.ayd.wineapp.ui.main.AllWineFragmentDirections
import com.ayd.wineapp.ui.main.MainWineFragmentDirections

class WineRowBinding {

    companion object {

        @BindingAdapter("loadImageUrl")
        @JvmStatic
        fun loadImageUrl(imageView: ImageView, imageUrl: String){
            imageView.load(imageUrl){
                crossfade(500)
            }
        }

        @BindingAdapter("onDetailWineClickListener")
        @JvmStatic
        fun onDetailWineClickListener(wineRowLayout: ConstraintLayout, detail: WineItem){
            wineRowLayout.setOnClickListener {
                try {
                    val action = MainWineFragmentDirections.actionMainWineFragmentToDetailWineFragment(detail)
                    wineRowLayout.findNavController().navigate(action)
                }catch (e: Exception){
                    Log.d("onDetailWineClick", e.toString())   //detailWine screen
                }
                try{
                val action2 = AllWineFragmentDirections.actionAllWineFragmentToDetailWineFragment(detail)
                wineRowLayout.findNavController().navigate(action2)
                }catch (e: Exception){
                    Log.d("onDetailWineClick2", e.toString())   //allWine screen
                }
            }
        }


    }

}