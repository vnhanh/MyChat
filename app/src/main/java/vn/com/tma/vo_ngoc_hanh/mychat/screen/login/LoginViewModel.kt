package vn.com.tma.vo_ngoc_hanh.mychat.screen.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.AppDatabase
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.asynctask.AddAccountAsyncTask
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.source.AccountLocalDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.source.AccountRepository
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.source.IAccountDataSource
import java.util.*

class LoginViewModel : AndroidViewModel {

    private lateinit var database:AppDatabase
    private var repository: IAccountDataSource ?= null

    constructor(app: Application) : super(app){
        database = AppDatabase.getInstance(app)

        val localDataSource = AccountLocalDataSource.getInstance(database.accountDAO())
        if (localDataSource != null) {
            repository = AccountRepository.getInstance(localDataSource)
        }
    }

    fun getAccounts(): LiveData<List<Account>>? {
        return repository?.getAllAccounts()
    }

    fun addName(name: String) {
        val account = Account(name, true, Date(), "nghiamy15@gmail.com")
        Log.d("LOG", "add new account in view model")

        AddAccountAsyncTask(repository).execute(account)
    }
}