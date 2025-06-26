package com.grhuan.cat.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val id: String,
    val url: String,
    val breeds: List<Breed>
)