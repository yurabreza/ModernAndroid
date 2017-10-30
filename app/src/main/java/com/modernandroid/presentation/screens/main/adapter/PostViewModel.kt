package com.modernandroid.presentation.screens.main.adapter

import android.databinding.ObservableField
import android.databinding.ObservableInt


class PostViewModel(_userId: Int, _id: Int, _title: String, _body: String) {
    val userId: ObservableInt = ObservableInt(_userId)
    val id: ObservableInt = ObservableInt(_id)
    val title: ObservableField<String> = ObservableField(_title)
    val body: ObservableField<String> = ObservableField(_body)
}