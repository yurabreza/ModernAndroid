package com.modernandroid.presentation.app.di

import com.modernandroid.presentation.app.App
import com.modernandroid.presentation.screens.main.di.MainActivityComponent

class DependencyGraph(mainApp: App) {
    private val mAppComponent: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(mainApp))
            .build()

    private var mainActivityComponent: MainActivityComponent? = null

    fun initMainActivityComponent(): MainActivityComponent? {
        mainActivityComponent = mAppComponent.plusMainActivityComponent()
        return mainActivityComponent
    }

    fun releaseMainActivityComponent() {
        mainActivityComponent = null
    }
}
