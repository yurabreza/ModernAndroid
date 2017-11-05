package com.modernandroid.presentation.screens.main.di

import com.modernandroid.presentation.screens.main.MainViewModel
import com.modernandroid.presentation.screens.main.PerActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainViewModelComponent::class))
@PerActivity interface MainViewModelComponent {
    fun inject(viewModel: MainViewModel)
}