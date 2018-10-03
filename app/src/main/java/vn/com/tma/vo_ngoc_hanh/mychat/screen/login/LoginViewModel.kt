package vn.com.tma.vo_ngoc_hanh.mychat.screen.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.AppDatabase
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.asynctask.AddAccountAsyncTask
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.AccountLocalDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.AccountRepository
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.IAccountDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.di.Injection
import java.util.*

class LoginViewModel : AndroidViewModel {

    private lateinit var database:AppDatabase
    private var repository: IAccountDataSource ?= null

    constructor(app: Application) : super(app){
        database = AppDatabase.getInstance(app)

        repository = Injection.injectAccountRepository(AppDatabase.getInstance(app).accountDAO())
    }

    fun getAccounts(): LiveData<List<Account>>? {
        return repository?.getAllAccounts()
    }

    fun addName(name: String) {
        val account = Account(name, true, Date(), "nghiamy15@gmail.com")

        AddAccountAsyncTask(repository).execute(account)
    }
}