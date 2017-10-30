package com.modernandroid.presentation.screens.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.modernandroid.R
import com.modernandroid.databinding.ActivityMainBinding
import com.modernandroid.presentation.Navigator
import com.modernandroid.presentation.app.App
import com.modernandroid.presentation.dataRepository.PostsRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Navigator {
    lateinit var mainActivityViewModel: MainViewModel

    @Inject lateinit var postsRepository: PostsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.dependencyGraph?.initMainActivityComponent()?.inject(this)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = MainViewModel(activityMainBinding, postsRepository, this)
    }

    override fun displayPostDetails(postId: Int) {
            //        todo
    }

    override fun onDestroy() {
        super.onDestroy()
        App.dependencyGraph?.releaseMainActivityComponent()
    }
}
