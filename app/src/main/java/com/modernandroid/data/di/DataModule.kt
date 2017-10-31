package com.modernandroid.data.di

import com.modernandroid.data.api.ApiService
import com.modernandroid.data.repository.PostsRepositoryImpl
import com.modernandroid.presentation.dataRepository.PostsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module @Singleton class DataModule{
    @Provides fun providePostsRepository(apiService: ApiService): PostsRepository = PostsRepositoryImpl(apiService)
}
