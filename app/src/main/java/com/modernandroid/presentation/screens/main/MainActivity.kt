package com.modernandroid.presentation.screens.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
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
        Log.d(TAG, "onCreate")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.model = viewModel
        binding.executePendingBindings()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = this.adapter

        binding.model?.posts?.observe(this, Observer<ArrayList<Post>> {
            Log.d(TAG, "+ ${it?.size} posts added ")
            it?.let { posts.addAll(it) }
            adapter.notifyDataSetChanged()
        })

        binding.model?.error?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                Log.d(TAG, "error ")
                Toast.makeText(applicationContext, "Error : ${binding.model?.error?.get()}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.model?.progressVisible?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                Log.d(TAG, "binding.model?.progressVisible? ${binding.model?.progressVisible?.get()}")
            }
        })

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun displayPostDetails(postId: Int) {
        Toast.makeText(this, "postId $postId", Toast.LENGTH_SHORT).show()
    }

    companion object {
        val TAG = "MainActivityTag"
    }
}
