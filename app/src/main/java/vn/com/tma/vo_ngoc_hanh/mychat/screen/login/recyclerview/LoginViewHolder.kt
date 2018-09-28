package vn.com.tma.vo_ngoc_hanh.mychat.screen.login.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_message.view.*
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account

class LoginViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    var account:Account ?= null

    fun bind(account: Account) {
        this.account = account

        itemView.tv_content.text = account.name
    }
}