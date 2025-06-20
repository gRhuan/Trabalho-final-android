package com.grhuan.cat.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grhuan.cat.data.model.CatEntity
import com.grhuan.cat.databinding.ItemCatBinding

class CatAdapter : RecyclerView.Adapter<CatAdapter.CatViewHolder>(){

    private var cats: List<CatEntity> = emptyList()

    fun submitList(newCats: List<CatEntity>) {
        cats = newCats
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(cats[position])
    }

    override fun getItemCount(): Int = cats.size

    class CatViewHolder(private val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CatEntity) {
            binding.tvName.text = item.id
            Glide.with(binding.root.context)
                .load(item.url)
                .into(binding.ivIcon)
        }
    }
}