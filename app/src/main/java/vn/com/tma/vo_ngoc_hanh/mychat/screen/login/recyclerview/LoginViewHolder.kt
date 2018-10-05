package vn.com.tma.vo_ngoc_hanh.mychat.screen.login.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_message.view.*
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal

class LoginViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    var account: AccountLocal?= null

    fun bind(account: AccountLocal) {
        this.account = account

        itemView.tv_content.text = account.fullname
    }
}