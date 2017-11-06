package com.modernandroid.presentation.mapper

import com.modernandroid.data.model.Post
import com.modernandroid.presentation.screens.main.adapter.PostViewModel
import java.util.function.Function

class PostToPostViewModelMapper : Function<Post, PostViewModel> {
    override fun apply(t: Post): PostViewModel = PostViewModel(t.id!!, t.title!!, t.body!!)
}