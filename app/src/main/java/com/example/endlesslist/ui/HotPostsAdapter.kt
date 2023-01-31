package com.example.endlesslist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.endlesslist.databinding.ItemPostBinding
import com.example.endlesslist.domain.HotPostDTO

class HotPostsAdapter :
    PagingDataAdapter<HotPostDTO, HotPostsAdapter.HotPostsViewHolder>(HotPostsComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotPostsViewHolder {
        return HotPostsViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HotPostsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class HotPostsViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HotPostDTO) = with(binding) {
            itemPostTitle.text = item.data.title
            itemPostCommentsValue.text = item.data.num_comments.toString()
            itemPostCrosspostsValue.text = item.data.num_crossposts.toString()
        }
    }

    object HotPostsComparator : DiffUtil.ItemCallback<HotPostDTO>() {
        override fun areItemsTheSame(oldItem: HotPostDTO, newItem: HotPostDTO): Boolean {
            return oldItem.data.title == newItem.data.title
        }

        override fun areContentsTheSame(oldItem: HotPostDTO, newItem: HotPostDTO): Boolean {
            return oldItem == newItem
        }
    }
}