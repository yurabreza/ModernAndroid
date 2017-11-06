package com.modernandroid.presentation.screens.main.adapter

import android.databinding.ObservableField
import android.databinding.ObservableInt


class PostViewModel(id: Int, title: String, body: String) {
    val id: ObservableInt = ObservableInt(id)
    val title: ObservableField<String> = ObservableField(title)
    val body: ObservableField<String> = ObservableField(body)
}