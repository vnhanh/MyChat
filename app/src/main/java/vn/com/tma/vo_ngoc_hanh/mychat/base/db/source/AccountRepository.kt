package vn.com.tma.vo_ngoc_hanh.mychat.base.db.source

import android.arch.lifecycle.LiveData
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account

class AccountRepository private constructor(var localDataSource: IAccountDataSource) : IAccountDataSource {

    companion object {
        private var INSTANCE : AccountRepository ?= null
        fun getInstance(localDataSource: IAccountDataSource) : IAccountDataSource?{
            if (INSTANCE == null) {
                INSTANCE = AccountRepository(localDataSource)
            }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getAllAccounts(): LiveData<List<Account>> {
        return localDataSource.getAllAccounts()
    }

    override fun getAccountById(id: Int): LiveData<Account> {
        return localDataSource.getAccountById(id)
    }

    override fun getAccountByEmail(email: String): LiveData<Account> {
        return localDataSource.getAccountByEmail(email)
    }

    override fun addAccount(account: Account) {
        localDataSource.addAccount(account)
    }

    override fun updateAccount(account: Account) {
        localDataSource.updateAccount(account)
    }

    override fun deleteAccount(account: Account) {
        localDataSource.deleteAccount(account)
    }

    override fun deleteAllAccounts() {
        localDataSource.deleteAllAccounts()
    }
}