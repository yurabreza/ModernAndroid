package com.modernandroid.presentation.screens.main.di

import com.modernandroid.presentation.screens.main.MainActivity
import com.modernandroid.presentation.screens.main.PerActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainActivityModule::class)) @PerActivity interface MainActivityComponent {
    fun inject(activity: MainActivity)
}