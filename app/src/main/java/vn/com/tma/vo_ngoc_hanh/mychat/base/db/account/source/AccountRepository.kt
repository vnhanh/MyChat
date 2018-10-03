package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source

import android.arch.lifecycle.LiveData
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account

class AccountRepository private constructor(var localDataSource: IAccountDataSource, var remoteDataSource: IAccountDataSource) : IAccountDataSource {

    companion object {
        private var INSTANCE : AccountRepository ?= null
        fun getInstance(localDataSource: IAccountDataSource, remoteDataSource: IAccountDataSource) : IAccountDataSource{
            if (INSTANCE == null) {
                INSTANCE = AccountRepository(localDataSource, remoteDataSource)
            }

            return INSTANCE!!
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

    override fun createAccount(account: Account, password:String) : Observable<Boolean> {
        return remoteDataSource.createAccount(account, password)
                .flatMap({isSuccess -> if (isSuccess) {

                    localDataSource.createAccount(account, password)
                        }else{

                            Observable.just(false)
                        }
                })
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