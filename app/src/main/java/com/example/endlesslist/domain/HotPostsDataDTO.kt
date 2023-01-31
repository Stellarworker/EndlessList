package com.example.endlesslist.domain

data class HotPostsDataDTO(
    val after: String,
    val dist: Int,
    val modhash: String,
    val geo_filter: String,
    val children: List<HotPostDTO>,
    val before: String
)
