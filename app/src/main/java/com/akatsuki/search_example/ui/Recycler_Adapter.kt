package com.akatsuki.search_example.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.akatsuki.search_example.data.model.Product
import com.akatsuki.search_example.databinding.ItemLayoutBinding

class Recycler_Adapter : RecyclerView.Adapter<Recycler_Adapter.MyViewHolder>(){

    inner class MyViewHolder(val binding: ItemLayoutBinding):
            RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    var products: List<Product>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = products[position]
        holder.binding.ProductImg.load(item.thumbnail)
        holder.binding.textBrand.text = item.brand.toString()
        holder.binding.textPrice.text = "$" + item.price.toString()
        holder.binding.textTitle.text = item.title.toString()
    }

    override fun getItemCount(): Int {
       return products.size
    }


}