package com.modernandroid.presentation.screens.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.modernandroid.data.model.Post
import com.modernandroid.presentation.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    @Inject lateinit var presenter: MainPresenter
    val progressVisible = ObservableBoolean(false)
    var posts = MutableLiveData<ArrayList<Post>>()
    var error = ObservableField<String>()

    init {
        App.dependencyGraph?.initMainViewModelComponent()?.inject(this)

        disposable.add(presenter.getPostsList()
                .doOnSubscribe({ progressVisible.set(true) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ progressVisible.set(false) })
                .doOnSuccess({ progressVisible.set(false) })
                .subscribe(this::updatePosts, this::onError))
    }

    private fun updatePosts(posts: List<Post>) {
        this.posts.value = ArrayList(posts)
    }

    private fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        error.set("No internet connection")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        App.dependencyGraph?.releaseMainViewModelComponent()
    }
}