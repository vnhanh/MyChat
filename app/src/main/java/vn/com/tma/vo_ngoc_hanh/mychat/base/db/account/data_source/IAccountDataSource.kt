package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source

import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal

interface IAccountDataSource {
    fun getAllAccounts() : LiveData<List<AccountLocal>>

    fun getAccountById(id:Int): LiveData<AccountLocal>

    fun getAccountByEmail(email:String): LiveData<AccountLocal>

    fun registerOrAddAccount(account: AccountLocal, password:String) : Observable<Boolean>

    fun signin(email:String, password: String) : Observable<Boolean>

    fun updateAccount(account: AccountLocal)

    fun deleteAccount(account: AccountLocal)

    fun deleteAllAccounts()
}