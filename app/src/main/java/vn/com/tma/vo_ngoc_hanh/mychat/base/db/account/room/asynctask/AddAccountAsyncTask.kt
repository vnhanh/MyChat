package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.asynctask

import android.os.AsyncTask
import io.reactivex.ObservableEmitter
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source.IAccountDataSource

class AddAccountAsyncTask(var dataSource: IAccountDataSource?) : AsyncTask<AccountLocal, Void, Boolean>() {
    private var emitter: ObservableEmitter<Boolean>?=null

    constructor(dataSource: IAccountDataSource?, emitter:ObservableEmitter<Boolean>) : this(dataSource){
        this.emitter = emitter
    }

    override fun doInBackground(vararg params: AccountLocal?): Boolean {
        val account = params[0] as AccountLocal

        dataSource?.addAccount(account)
        return true
    }

    override fun onPostExecute(result: Boolean) {
        if (emitter != null) {
            emitter!!.onNext(result)
        }
    }
}