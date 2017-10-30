package com.modernandroid.presentation.screens.main.di

import com.modernandroid.presentation.dataRepository.PostsRepository
import com.modernandroid.presentation.screens.main.PerActivity
import dagger.Module
import dagger.Provides

@Module class MainActivityModule {
    @Provides @PerActivity fun providePostsRepository(postsRepository: PostsRepository): PostsRepository = postsRepository
}