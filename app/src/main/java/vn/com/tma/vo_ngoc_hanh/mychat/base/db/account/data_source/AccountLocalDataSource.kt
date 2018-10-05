package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source

import android.arch.lifecycle.LiveData
import android.util.Log
import io.reactivex.Observable
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountDAO
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.asynctask.AddAccountAsyncTask

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


    override fun getAllAccounts(): LiveData<List<AccountLocal>> {
        return dao.getAllAccounts()
    }

    override fun getAccountById(id: Int): LiveData<AccountLocal> {
        return dao.getAccountById(id)
    }

    override fun getAccountByEmail(email: String): LiveData<AccountLocal> {
        return dao.getAccountByEmail(email)
    }

    override fun registerOrAddAccount(account: AccountLocal, password: String) : Observable<Boolean> {
        return Observable.create<Boolean>({
            AddAccountAsyncTask(this, it).execute(account)
        })
    }

    override fun signin(email: String, password: String): Observable<Boolean> {
        return Observable.just(true)
    }

    override fun updateAccount(account: AccountLocal) {
        dao.updateAccount(account)
    }

    override fun deleteAccount(account: AccountLocal) {
        dao.deleteAccount(account)
    }

    override fun deleteAllAccounts() {
        dao.deleteAllAccounts()
    }
}