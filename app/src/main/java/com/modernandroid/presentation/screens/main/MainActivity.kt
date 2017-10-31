package com.modernandroid.presentation.screens.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.modernandroid.R
import com.modernandroid.databinding.ActivityMainBinding
import com.modernandroid.presentation.app.App
import com.modernandroid.presentation.screens.Navigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Navigator {
    lateinit var mainActivityViewModel: MainViewModel

    @Inject lateinit var mainPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.dependencyGraph?.initMainActivityComponent()?.inject(this)
        mainPresenter.onAttach(this)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = MainViewModel(activityMainBinding, mainPresenter, this)
    }

    override fun displayPostDetails(postId: Int) {
            //        todo
    }

    override fun onDestroy() {
        super.onDestroy()
        App.dependencyGraph?.releaseMainActivityComponent()
        mainPresenter.onDetach()
    }
}
