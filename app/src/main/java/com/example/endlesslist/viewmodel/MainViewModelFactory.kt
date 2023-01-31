package com.example.endlesslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.endlesslist.repository.RedditAPI

class MainViewModelFactory(
    private val api: RedditAPI
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(api) as T
    }
}