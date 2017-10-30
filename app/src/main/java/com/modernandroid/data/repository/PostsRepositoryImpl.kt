package com.modernandroid.data.repository

import com.modernandroid.data.api.ApiService
import com.modernandroid.data.model.Comment
import com.modernandroid.data.model.Post
import com.modernandroid.presentation.dataRepository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(private val apiService: ApiService) : PostsRepository {
    override fun postById(id: String):Single<Post> {
      return apiService.postById(id)
    }

    override fun posts(): Single<List<Post>>  {
       return apiService.posts()
    }

    override fun postCommentsById(id: String): Single<List<Comment>> {
        return apiService.postCommentsById(id)
    }
}