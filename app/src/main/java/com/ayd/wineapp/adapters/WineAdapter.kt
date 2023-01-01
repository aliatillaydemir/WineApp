package com.ayd.wineapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayd.wineapp.databinding.WineRowLayoutBinding
import com.ayd.wineapp.model.Wine
import com.ayd.wineapp.model.WineItem
import com.ayd.wineapp.utils.WineDiffUtil

class WineAdapter: RecyclerView.Adapter<WineAdapter.MyViewHolder>() {
    private var wines = emptyList<WineItem>()

    class MyViewHolder(private val binding: WineRowLayoutBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(result: WineItem){
            binding.result = result               //<variable> name in wine_row_layout
            binding.executePendingBindings()    //update data inside layout change whatever
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WineRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentWine = wines[position]
        holder.bind(currentWine)

    }

    override fun getItemCount(): Int {
        return wines.size
    }

    fun setData(newData: Wine){
        val wineDiffUtil = WineDiffUtil(wines,newData)
        val diffUtilResult = DiffUtil.calculateDiff(wineDiffUtil)

        wines = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun setDataSearch(newData: List<WineItem>){
        val wineDiffUtil = WineDiffUtil(wines,newData)
        val diffUtilResult = DiffUtil.calculateDiff(wineDiffUtil)

        wines = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }



}