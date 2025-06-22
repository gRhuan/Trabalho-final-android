package com.grhuan.cat.presentation.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.grhuan.cat.data.model.CatEntity
import com.grhuan.cat.databinding.ItemCatBinding


class CatAdapter(
    private val onClickListener: (String) -> Unit
) : ListAdapter<CatEntity, CatViewHolder>(CatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }
}