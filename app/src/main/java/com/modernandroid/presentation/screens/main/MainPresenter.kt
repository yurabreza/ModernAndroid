package com.modernandroid.presentation.screens.main

import com.modernandroid.data.model.Post
import com.modernandroid.presentation.dataRepository.PostsRepository
import io.reactivex.Single

class MainPresenter( private val postsRepo: PostsRepository) {

    private var mainActivity: MainActivity? = null
    fun onAttach(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }

    fun getPostsList(): Single<List<Post>> = postsRepo.posts()

    fun onDetach() {
        this.mainActivity = null
    }
}