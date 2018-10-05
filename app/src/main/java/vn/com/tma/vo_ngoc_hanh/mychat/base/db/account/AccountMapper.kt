package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account

import android.animation.TypeConverter
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.firebase.AccountRemote
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal
import vn.com.tma.vo_ngoc_hanh.mychat.common.util.Util

class AccountMapper {
    companion object {
        fun localToRemote(account: AccountLocal) : AccountRemote{
            val accountRemote = AccountRemote()
            accountRemote.fullName = account.fullname
            accountRemote.email = account.email
            accountRemote.gender = account.gender
            accountRemote.birthDate = account.birthDate.time

            return accountRemote
        }
    }
}