package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.asynctask

import android.os.AsyncTask
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.source.AccountRepository
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.source.IAccountDataSource

class AddAccountAsyncTask(var repository: IAccountDataSource?) : AsyncTask<Account, Void, Boolean>() {
    override fun doInBackground(vararg params: Account?): Boolean {
        val account = params[0]
        if (account != null) {
            repository?.addAccount(account)
        }
        return true
    }
}