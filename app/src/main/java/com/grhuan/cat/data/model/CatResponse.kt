package com.grhuan.cat.data.model

data class CatResponse(
    val id: String,
    val url: String,
    val breeds: List<Breed>,
)

data class Breed(
    val name: String,
    val description: String,
)