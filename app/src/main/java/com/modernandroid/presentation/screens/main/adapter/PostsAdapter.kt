package com.modernandroid.presentation.screens.main.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.modernandroid.R
import com.modernandroid.data.model.Post
import com.modernandroid.databinding.ItemPostBinding

class PostsAdapter(private val posts: ArrayList<Post>, private val listener: (Post) -> Unit) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return PostsViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_post, parent, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) = holder.bind(posts[position], listener)

    class PostsViewHolder(private val itemPostBinding: ItemPostBinding) : RecyclerView.ViewHolder(itemPostBinding.root) {

        fun bind(post: Post, listener: (Post) -> Unit) {
            val postViewModel = PostViewModel(post.userId!!, post.id!!, post.title!!, post.body!!)
            itemPostBinding.post = postViewModel
            itemPostBinding.executePendingBindings()
            itemPostBinding.root.setOnClickListener({ listener.invoke(post) })
        }

    }
}