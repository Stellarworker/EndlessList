package com.example.endlesslist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.endlesslist.databinding.ActivityMainBinding
import com.example.endlesslist.repository.RedditAPI
import com.example.endlesslist.viewmodel.MainViewModel
import com.example.endlesslist.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var hotPostsAdapter: HotPostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initAdapter()
        initList()
    }

    private fun initViewModel() {
        val factory = MainViewModelFactory(RedditAPI())
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun initAdapter() {
        hotPostsAdapter = HotPostsAdapter()
        binding.activityMainList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = hotPostsAdapter
            setHasFixedSize(true)
        }
    }

    private fun initList() {
        lifecycleScope.launch {
            mainViewModel.hotPosts.collectLatest { pagedData ->
                hotPostsAdapter.submitData(pagedData)
            }
        }
    }

}