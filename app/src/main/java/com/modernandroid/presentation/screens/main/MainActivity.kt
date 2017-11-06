package com.modernandroid.presentation.screens.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.modernandroid.R
import com.modernandroid.data.model.Post
import com.modernandroid.databinding.ActivityMainBinding
import com.modernandroid.presentation.screens.Navigator
import com.modernandroid.presentation.screens.main.adapter.PostsAdapter

class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var binding: ActivityMainBinding

    private val posts: ArrayList<Post> = ArrayList()
    private val adapter: PostsAdapter = PostsAdapter(posts, { post -> displayPostDetails(post.id!!) })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = this.adapter

        binding.model?.posts?.observe(this, Observer<ArrayList<Post>> {
            it?.let { posts.addAll(it) }
            adapter.notifyDataSetChanged()
        })

        binding.model?.error?.observe(this, Observer<String> {
            it?.let { Toast.makeText(applicationContext, "Error : ${binding.model?.error?.value}", Toast.LENGTH_SHORT).show() }
        })
    }

    override fun displayPostDetails(postId: Int) {
        Toast.makeText(this, "postId $postId", Toast.LENGTH_SHORT).show()
    }
}
