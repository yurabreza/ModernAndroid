package com.modernandroid.presentation.app.di

import com.modernandroid.presentation.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(private val mMainApp: App) {
    @Provides @Singleton  fun provideMainApp(): App = mMainApp
}