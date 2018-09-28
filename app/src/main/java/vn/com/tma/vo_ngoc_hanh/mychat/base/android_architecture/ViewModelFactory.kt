package vn.com.tma.vo_ngoc_hanh.mychat.base.android_architecture

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import vn.com.tma.vo_ngoc_hanh.mychat.screen.login.LoginViewModel
import vn.com.tma.vo_ngoc_hanh.mychat.screen.register.RegisterViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory {
    lateinit var app:Application

    companion object {
        private var INSTANCE:ViewModelFactory ?= null

        fun getInstance(app:Application) : ViewModelFactory{
            return INSTANCE?:ViewModelFactory(app)
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    private constructor(app: Application) {
        this.app = app
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(app) as T
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java))
            return RegisterViewModel(app) as T
        return super.create(modelClass)
    }
}