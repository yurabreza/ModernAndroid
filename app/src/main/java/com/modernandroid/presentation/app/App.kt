package com.modernandroid.presentation.app

import android.app.Application
import com.modernandroid.presentation.app.di.DependencyGraph
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        dependencyGraph = DependencyGraph(this)
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name(DATABASE)
                .schemaVersion(1)
                .build()
        Realm.setDefaultConfiguration(config)
    }

    companion object {
        val DATABASE = "database"
        var dependencyGraph: DependencyGraph? = null
            private set
    }
}


