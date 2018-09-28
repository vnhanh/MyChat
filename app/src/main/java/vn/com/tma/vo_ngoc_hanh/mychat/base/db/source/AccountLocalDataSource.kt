package vn.com.tma.vo_ngoc_hanh.mychat.base.db.source

import android.arch.lifecycle.LiveData
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.AccountDAO

class AccountLocalDataSource private constructor(var dao: AccountDAO) : IAccountDataSource {

    companion object {
        private var INSTANCE : AccountLocalDataSource ?= null

        fun getInstance(dao: AccountDAO) : AccountLocalDataSource? {
            if (INSTANCE == null) {
                INSTANCE = AccountLocalDataSource(dao)
            }

            return INSTANCE
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

    override fun addAccount(account: Account) {
        dao.addAccount(account)
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