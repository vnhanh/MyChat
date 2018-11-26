package vn.com.tma.vo_ngoc_hanh.mychat.screen.login

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.disposables.Disposable
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.AppDatabase
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source.IAccountDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.di.Injection
import vn.com.tma.vo_ngoc_hanh.mychat.common.android_architecture.BaseAndroidViewModel

class LoginViewModel(app:Application) : BaseAndroidViewModel(app) {

    private lateinit var repository: IAccountDataSource

    var signinResult = MutableLiveData<Boolean>()
    var clearInputState = ObservableBoolean(false)
    var isLoading = ObservableBoolean(false)
    var validatedTrigger = MutableLiveData<Boolean>()

    init{
        signinResult.value = false
        validatedTrigger.value = false
        repository = Injection.injectAccountRepository(AppDatabase.getInstance(app).accountDAO())
    }

    override fun onCreate() {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            noticeLogined()
        }
    }

    fun onSubmit(email: String, password: String, isValidated:Boolean) {
        if (!isValidated) {
            validatedTrigger.value = !validatedTrigger.value!!
        }else{
            showLoadingUI()

            addDisposable(setupSigninAction(email, password))
        }
    }

    private fun setupSigninAction(email: String, password: String) : Disposable {
        return repository.signin(email, password)
                .subscribe { isSuccess ->
                    hideLoadingUI()
                    if (isSuccess) {
                        noticeLogined()
                    }else{
                        clearInputState.set(!clearInputState.get())
                    }
                }
    }

    private fun noticeLogined() {
        signinResult.value = true
    }

    private fun showLoadingUI() {
        isLoading.set(true)
    }

    private fun hideLoadingUI() {
        isLoading.set(false)
    }
}