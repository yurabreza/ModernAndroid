package com.modernandroid.presentation.screens.main

import android.view.View
import android.widget.ProgressBar

interface HasProgress {
    val progressBar: ProgressBar

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}