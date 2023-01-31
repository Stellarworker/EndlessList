package com.example.endlesslist.domain

data class HotPostDataDTO(
    val title: String,
    val name: String,
    val num_comments: Int,
    val num_crossposts: Int
)