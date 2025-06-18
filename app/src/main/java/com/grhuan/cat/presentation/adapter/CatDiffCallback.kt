package com.grhuan.cat.presentation.adapter


import androidx.recyclerview.widget.DiffUtil
import com.grhuan.cat.data.model.CatResponse

class CatDiffCallback : DiffUtil.ItemCallback<CatResponse>() {

    override fun areItemsTheSame(oldItem: CatResponse, newItem: CatResponse): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CatResponse, newItem: CatResponse): Boolean =
        oldItem == newItem
}