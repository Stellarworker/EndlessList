package com.example.endlesslist.repository

import com.example.endlesslist.domain.HotPostsDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditAPI {
    @GET("hot.json")
    suspend fun getHotPosts(
        @Query("after") after: String?,
        @Query("limit") limit: Int?
    ): HotPostsDTO

    companion object {
        private const val BASE_URL = "https://www.reddit.com/r/aww/"
        operator fun invoke(): RedditAPI = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RedditAPI::class.java)
    }
}