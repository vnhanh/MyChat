package vn.com.tma.vo_ngoc_hanh.mychat.screen.register

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.AppDatabase
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source.IAccountDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.di.Injection
import vn.com.tma.vo_ngoc_hanh.mychat.common.android_architecture.BaseAndroidViewModel

class RegisterViewModel(app:Application) : BaseAndroidViewModel(app){

    var registerResult = MutableLiveData<Boolean>()
    var isLoading = MutableLiveData<Boolean>()
    private lateinit var repository:IAccountDataSource
    var isRegistered = false
    var clearInputState = ObservableBoolean(false)

    init{
        registerResult.value = false
        isLoading.value = false

        repository = Injection.injectAccountRepository(AppDatabase.getInstance(app).accountDAO())
    }

    override fun onCreate() {

    }

    fun onSubmit(account: AccountLocal, password:String) {
        showLoadingUI()

        // 6 years and 8 months (02/2012 -> 10/2018)
        addDisposable(
            repository.register(account, password)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({isSuccess ->

                    changeUIOnReceiveRegisterResult()
                    registerResult.value = true
                }, {e ->
                    Log.d("LOG", "RegisterViewModel():onSubmit:register error !!!: " + e.message)

                    changeUIOnReceiveRegisterResult()
                    registerResult.value = false
                }
        ))
    }

    private fun changeUIOnReceiveRegisterResult() {
        hideLoadingUI()
        clearInputs()
    }

    private fun clearInputs() {
        clearInputState.set(!clearInputState.get())
    }

    private fun showLoadingUI() {
        isLoading.value = true
    }

    private fun hideLoadingUI() {
        isRegistered = true
        isLoading.value = false
    }
}