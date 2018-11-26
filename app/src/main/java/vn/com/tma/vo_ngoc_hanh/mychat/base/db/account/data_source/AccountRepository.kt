package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source

import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal

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

    override fun getAllAccounts(): LiveData<List<AccountLocal>> {
        return localDataSource.getAllAccounts()
    }

    override fun getAccountById(uid: String): LiveData<AccountLocal> {
        return localDataSource.getAccountById(uid)
    }

    override fun getAccountByEmail(email: String): LiveData<AccountLocal> {
        return localDataSource.getAccountByEmail(email)
    }

    override fun registerOrAddAccount(account: AccountLocal, password:String) : Observable<Boolean> {
        return remoteDataSource.registerOrAddAccount(account, password)
                .flatMap({isSuccess -> if (isSuccess) {

                    localDataSource.registerOrAddAccount(account, password)
                        }else{

                    Observable.just(false)
                        }
                })
    }

    override fun signin(email: String, password: String): Observable<Boolean> {
        return remoteDataSource.signin(email, password)
    }

    override fun updateAccount(account: AccountLocal) {
        localDataSource.updateAccount(account)
    }

    override fun deleteAccount(account: AccountLocal) {
        localDataSource.deleteAccount(account)
    }

    override fun deleteAllAccounts() {
        localDataSource.deleteAllAccounts()
    }
}