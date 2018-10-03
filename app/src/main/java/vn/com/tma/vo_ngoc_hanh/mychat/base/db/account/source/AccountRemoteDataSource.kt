package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source

import android.arch.lifecycle.LiveData
import android.util.Log
import android.view.LayoutInflater
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account

class AccountRemoteDataSource : IAccountDataSource {
    private val auth = FirebaseAuth.getInstance()

    private constructor()

    companion object {
        private var INSTANCE:AccountRemoteDataSource?=null

        fun getInstance() : IAccountDataSource{
            if (INSTANCE == null) {
                INSTANCE = AccountRemoteDataSource()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getAllAccounts(): LiveData<List<Account>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAccountById(id: Int): LiveData<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAccountByEmail(email: String): LiveData<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createAccount(account: Account, password:String) : Observable<Boolean> {
        return Observable.create<Boolean>{
            auth.createUserWithEmailAndPassword(account.email, password)
                    .addOnCompleteListener{task ->
                        it.onNext(task.isSuccessful)
                    }.addOnFailureListener{e ->
                        Log.d("LOG", "register fail - exception: " + e.message)
                        it.onError(e)
                    }
        }
    }

    override fun updateAccount(account: Account) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAccount(account: Account) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllAccounts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}