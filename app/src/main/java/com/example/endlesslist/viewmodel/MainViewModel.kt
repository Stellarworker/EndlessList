package com.example.endlesslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.endlesslist.data.HotPostsPagingSource
import com.example.endlesslist.repository.RedditAPI

class MainViewModel(
    private val repository: RedditAPI
) : ViewModel() {
    val hotPosts = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = { HotPostsPagingSource(repository) }).flow.cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 20
    }
}