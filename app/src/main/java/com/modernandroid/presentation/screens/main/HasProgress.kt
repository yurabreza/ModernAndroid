package com.modernandroid.presentation.screens.main

import android.view.View
import android.widget.ProgressBar

interface HasProgress {
    fun getProgressBar():ProgressBar?

    fun showProgress() {
        getProgressBar()?.visibility = View.VISIBLE
    }

    fun hideProgress() {
        getProgressBar()?.visibility = View.GONE
    }
}