package com.modernandroid.presentation.dataRepository

import com.modernandroid.data.model.Comment
import com.modernandroid.data.model.Post
import io.reactivex.Single

interface PostsRepository {
    fun postById( id: String): Single<Post>

    fun posts(): Single<List<Post>>

    fun postCommentsById(id: String): Single<List<Comment>>
}
