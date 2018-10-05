package vn.com.tma.vo_ngoc_hanh.mychat.screen.login.recyclerview

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal

class LoginAdapter : RecyclerView.Adapter<LoginViewHolder> {
    private lateinit var list : List<AccountLocal>

    constructor() : super(){
        list = ArrayList<AccountLocal>()
    }

    fun addList(_list: List<AccountLocal>) {
        list = _list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, typeView: Int): LoginViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val viewHolder = LoginViewHolder(inflater.inflate(R.layout.item_message, parent, false))

        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) {
        Log.d("LOG", "bind view holder")
        holder.bind(list.get(position))
    }
}