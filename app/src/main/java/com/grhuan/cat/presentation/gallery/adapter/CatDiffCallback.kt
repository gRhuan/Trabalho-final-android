package com.grhuan.cat.presentation.gallery.adapter


import androidx.recyclerview.widget.DiffUtil
import com.grhuan.cat.data.model.CatEntity

class CatDiffCallback : DiffUtil.ItemCallback<CatEntity>() {

    override fun areItemsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean =
        oldItem == newItem
}