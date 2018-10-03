package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source

import android.arch.lifecycle.LiveData
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.AccountDAO
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.asynctask.AddAccountAsyncTask

class AccountLocalDataSource private constructor(var dao: AccountDAO) : IAccountDataSource {

    companion object {
        private var INSTANCE : AccountLocalDataSource ?= null

        fun getInstance(dao: AccountDAO) : AccountLocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = AccountLocalDataSource(dao)
            }

            return INSTANCE!!
        }
    }


    override fun getAllAccounts(): LiveData<List<Account>> {
        return dao.getAllAccounts()
    }

    override fun getAccountById(id: Int): LiveData<Account> {
        return dao.getAccountById(id)
    }

    override fun getAccountByEmail(email: String): LiveData<Account> {
        return dao.getAccountByEmail(email)
    }

    override fun createAccount(account: Account, password:String) : Observable<Boolean> {
        return Observable.create<Boolean>({
            Log.d("LOG", "LocalDataSource:createAccount()")
            AddAccountAsyncTask(this, it).execute(account, password)
        })
    }

    override fun updateAccount(account: Account) {
        dao.updateAccount(account)
    }

    override fun deleteAccount(account: Account) {
        dao.deleteAccount(account)
    }

    override fun deleteAllAccounts() {
        dao.deleteAllAccounts()
    }
}