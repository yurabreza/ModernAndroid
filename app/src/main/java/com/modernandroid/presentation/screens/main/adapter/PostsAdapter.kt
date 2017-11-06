package com.modernandroid.presentation.screens.main.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.modernandroid.R
import com.modernandroid.data.model.Post
import com.modernandroid.databinding.ItemPostBinding
import com.modernandroid.presentation.mapper.PostToPostViewModelMapper

class PostsAdapter(private val posts: ArrayList<Post>,
                   private val listener: (Post) -> Unit) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostsViewHolder =
            PostsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.item_post, parent, false))

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) = holder.bind(posts[position], listener)

    class PostsViewHolder(private val itemPostBinding: ItemPostBinding) : RecyclerView.ViewHolder(itemPostBinding.root) {

        fun bind(post: Post, listener: (Post) -> Unit) {
            itemPostBinding.post = PostToPostViewModelMapper().apply(post)
            itemPostBinding.root.setOnClickListener({ listener.invoke(post) })
        }
    }
}

