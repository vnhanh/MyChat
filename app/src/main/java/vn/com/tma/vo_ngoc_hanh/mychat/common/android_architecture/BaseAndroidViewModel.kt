package vn.com.tma.vo_ngoc_hanh.mychat.common.android_architecture

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseAndroidViewModel(app:Application) : AndroidViewModel(app) {
    private val disposable = CompositeDisposable()

    protected fun addDisposable(_disposable: Disposable){
        disposable.add(_disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    abstract fun onCreate()
}