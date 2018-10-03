package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source

import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account

interface IAccountDataSource {
    fun getAllAccounts() : LiveData<List<Account>>

    fun getAccountById(id:Int): LiveData<Account>

    fun getAccountByEmail(email:String): LiveData<Account>

    fun createAccount(account: Account, password:String) : Observable<Boolean>

    fun updateAccount(account: Account)

    fun deleteAccount(account: Account)

    fun deleteAllAccounts()
}