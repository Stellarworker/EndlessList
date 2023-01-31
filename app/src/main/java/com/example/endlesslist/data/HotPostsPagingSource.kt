package com.example.endlesslist.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.endlesslist.domain.HotPostDTO
import com.example.endlesslist.repository.RedditAPI

class HotPostsPagingSource(
    private val service: RedditAPI
) : PagingSource<String, HotPostDTO>() {

    override val keyReuseSupported = true

    override fun getRefreshKey(state: PagingState<String, HotPostDTO>): String? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, HotPostDTO> {
        return try {
            val currentKey = params.key

            val response = service.getHotPosts(
                after = currentKey,
                limit = LIMIT_OF_POSTS
            )

            LoadResult.Page(
                data = response.data.children,
                prevKey = null,
                nextKey = response.data.children.last().data.name
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val LIMIT_OF_POSTS = 20
    }
}