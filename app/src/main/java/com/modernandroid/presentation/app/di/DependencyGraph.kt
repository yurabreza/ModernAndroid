package com.modernandroid.presentation.app.di

import com.modernandroid.presentation.app.App
import com.modernandroid.presentation.screens.main.di.MainViewModelComponent

class DependencyGraph(mainApp: App) {
    private val mAppComponent: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(mainApp))
            .build()

    private var mainActivityComponent: MainViewModelComponent? = null

    fun initMainViewModelComponent(): MainViewModelComponent? {
        mainActivityComponent = mAppComponent.plusMainViewModelComponent()
        return mainActivityComponent
    }

    fun releaseMainViewModelComponent() {
        mainActivityComponent = null
    }
}
