package com.modernandroid.presentation.screens.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.modernandroid.data.model.Post
import com.modernandroid.presentation.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    @Inject lateinit var dataSource: DataSource
    val progressVisible = ObservableBoolean(false)
    var posts = MutableLiveData<ArrayList<Post>>()
    var error = ObservableField<String>()

    init {
        App.dependencyGraph?.initMainViewModelComponent()?.inject(this)
        dataSource.onAttach(this)

        disposable.add(dataSource.getPostsList()
                .doOnSubscribe({ progressVisible.set(true) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ progressVisible.set(false) })
                .doOnSuccess({ progressVisible.set(false) })
                .subscribe(this::updatePosts, this::onError))
        Log.d(TAG, "init")
    }


    private fun updatePosts(posts: List<Post>) {
        Log.d(TAG,"${posts.size} posts loaded")
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
        dataSource.onDetach()
        Log.d(TAG, "onCleared")
    }

    companion object {
        val TAG = "MainViewModelTag"
    }
}