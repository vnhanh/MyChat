package vn.com.tma.vo_ngoc_hanh.mychat.screen.register

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.AppDatabase
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.IAccountDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.di.Injection
import vn.com.tma.vo_ngoc_hanh.mychat.common.android_architecture.BaseAndroidViewModel

class RegisterViewModel(app:Application) : BaseAndroidViewModel(app){

    var registerResult = MutableLiveData<Boolean>()
    var isLoading = MutableLiveData<Boolean>()
    private lateinit var repository:IAccountDataSource
    var isRegistered = false
    var isClearInput = ObservableBoolean(false)

    init{
        registerResult.value = false
        isLoading.value = false

        repository = Injection.injectAccountRepository(AppDatabase.getInstance(app).accountDAO())
    }

    fun onSubmit(account: Account, password:String) {
        showLoadingUI()

        // 6 years and 8 months (02/2012 -> 10/2018)
        addDisposable(
            repository.createAccount(account, password)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {isSuccess ->
                    Log.d("LOG", "RegisterViewModel():onSubmit:register success !!!")
                    isRegistered = true
                    hideLoadingUI()
                    registerResult.value = true
                }, {e ->
                    Log.d("LOG", "RegisterViewModel():onSubmit:register error !!!: " + e.message)
                    isRegistered = true
                    hideLoadingUI()
                    isClearInput.set(!isClearInput.get())
                    registerResult.value = false
                }
        ))
    }

    private fun showLoadingUI() {
        isLoading.value = true
    }

    private fun hideLoadingUI() {
        isLoading.value = false
    }
}