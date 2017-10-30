package com.modernandroid.presentation.screens.main

import android.support.v7.widget.LinearLayoutManager
import android.widget.ProgressBar
import android.widget.Toast
import com.modernandroid.R
import com.modernandroid.data.model.Post
import com.modernandroid.databinding.ActivityMainBinding
import com.modernandroid.presentation.Navigator
import com.modernandroid.presentation.dataRepository.PostsRepository
import com.modernandroid.presentation.screens.main.adapter.PostsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val activityMainBinding: ActivityMainBinding, repositoryImpl: PostsRepository,
                    navigator: Navigator) : HasProgress {

    override val progressBar: ProgressBar = activityMainBinding.progressBar
    private val posts: ArrayList<Post> = ArrayList()
    private val listener = { post: Post -> navigator.displayPostDetails(post.id!!) }
    private val adapter: PostsAdapter = PostsAdapter(posts, listener)

    init {
        activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(activityMainBinding.recyclerView.context)
        activityMainBinding.recyclerView.adapter = adapter
        repositoryImpl.posts()
                .doOnSubscribe({ showProgress() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ hideProgress() })
                .doOnSuccess({ hideProgress() })
                .subscribe(this::displayData, this::onError)
    }

    private fun displayData(posts: List<Post>) {
        this.posts.addAll(posts)
        adapter.notifyDataSetChanged()
    }

    private fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        val context = activityMainBinding.progressBar.context
        Toast.makeText(context, context.getString(R.string.error_fetch_data_api), Toast.LENGTH_SHORT).show()
    }
}