package com.grhuan.cat.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grhuan.cat.data.model.CatEntity
import com.grhuan.cat.data.model.CatResponse
import com.grhuan.cat.databinding.ItemCatBinding

class CatViewHolder(private val binding: ItemCatBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CatEntity){
        Glide.with(binding.root.context)
            .load(item.url)
            .into(binding.ivIcon)
        binding.tvName.text = item.id
    }
}