package com.modernandroid.presentation.screens.main

import com.modernandroid.data.model.Post
import com.modernandroid.presentation.dataRepository.PostsRepository
import io.reactivex.Single

class DataSource(private val postsRepo: PostsRepository) {

    private var mainViewModel: MainViewModel? = null
    fun onAttach(mainViewModel: MainViewModel) {
        this.mainViewModel = mainViewModel
    }

    fun getPostsList(): Single<List<Post>> = postsRepo.posts()

    fun onDetach() {
        this.mainViewModel = null
    }
}