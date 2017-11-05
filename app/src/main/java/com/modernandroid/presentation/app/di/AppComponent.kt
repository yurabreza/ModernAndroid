package com.modernandroid.presentation.app.di

import com.modernandroid.data.di.ApiModule
import com.modernandroid.data.di.DataModule
import com.modernandroid.presentation.screens.main.di.MainViewModelComponent
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, ApiModule::class, DataModule::class))
@Singleton interface AppComponent {
    fun plusMainViewModelComponent(): MainViewModelComponent
}
