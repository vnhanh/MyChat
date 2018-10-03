package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.asynctask

import android.os.AsyncTask
import io.reactivex.ObservableEmitter
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.IAccountDataSource

class AddAccountAsyncTask(var dataSource: IAccountDataSource?) : AsyncTask<Any, Void, Boolean>() {
    private var emitter: ObservableEmitter<Boolean>?=null

    constructor(dataSource: IAccountDataSource?, emitter:ObservableEmitter<Boolean>) : this(dataSource){
        this.emitter = emitter
    }

    override fun doInBackground(vararg params: Any?): Boolean {
        if (params.size == 2) {
            val account = params[0] as Account
            val password = params[1] as String

            dataSource?.createAccount(account, password)
            return true
        }
        return false
    }

    override fun onPostExecute(result: Boolean) {
        if (emitter != null) {
            emitter!!.onNext(result)
        }
    }
}