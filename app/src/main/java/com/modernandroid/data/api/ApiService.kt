package com.modernandroid.data.api

import com.modernandroid.data.model.Comment
import com.modernandroid.data.model.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/posts/{id}")
    fun postById(@Path("id") id: String): Single<Post>

    @GET("/posts")
    fun posts(): Single<List<Post>>

    @GET("/posts/{id}/comments")
    fun postCommentsById(@Path("id") id: String): Single<List<Comment>>
}