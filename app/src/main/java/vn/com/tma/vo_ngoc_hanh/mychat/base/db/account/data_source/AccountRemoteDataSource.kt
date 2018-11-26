package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source

import android.arch.lifecycle.LiveData
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.AccountMapper
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal

class AccountRemoteDataSource : IAccountDataSource {
    private val auth = FirebaseAuth.getInstance()
    private val firebaseDB = FirebaseDatabase.getInstance().reference

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

    override fun getAllAccounts(): LiveData<List<AccountLocal>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAccountById(uid: String): LiveData<AccountLocal> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAccountByEmail(email: String): LiveData<AccountLocal> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerOrAddAccount(account: AccountLocal, password:String) : Observable<Boolean> {
        return Observable.create<Boolean>{obsEmitter ->

            auth.createUserWithEmailAndPassword(account.email, password)

                    .addOnCompleteListener{task ->
                        if (task.isSuccessful) {
                            _writeNewAccount(obsEmitter, account, task)
                        }else{
                            obsEmitter.onNext(false)
                        }

                    }.addOnFailureListener{e ->

                        Log.d("LOG", "RemoteDataSource: register failed - exception: " + e.message)
                        emitError(obsEmitter, e)
                    }
        }
    }

    private fun _writeNewAccount(obsEmitter: ObservableEmitter<Boolean>, account: AccountLocal, task: Task<AuthResult>) {
        val uid = task.result.user.uid
        val accountRemote = AccountMapper.localToRemote(account)
        accountRemote.uid = uid

        firebaseDB.child("users").child(uid).setValue(accountRemote.toMap())
                .addOnSuccessListener {

                    emitOnNext(obsEmitter, true)
                }.addOnFailureListener { e ->
                    Log.d("LOG", "RemoteDataSource: create account failed - exception: " + e.message)

                    emitOnNext(obsEmitter, false)
                }
    }

    private fun <T> emitOnNext(emitter: ObservableEmitter<T>, value:T) {
        emitter.onNext(value)
    }

    private fun <T> emitError(emitter: ObservableEmitter<T>, throwable:Throwable) {
        emitter.onError(throwable)
    }

    override fun signin(email: String, password: String): Observable<Boolean> {
        return Observable.create<Boolean>{obsEmitter->
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            val uid = task.result.user.uid
                            obsEmitter.onNext(true)
                        }else{
                            obsEmitter.onNext(false)
                        }
                    }.addOnFailureListener { e ->
                        Log.d("LOG", "signin failed: exception: " + e.message)
                        obsEmitter.onError(e)
                    }
        }
    }

    override fun updateAccount(account: AccountLocal) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAccount(account: AccountLocal) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllAccounts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}