package vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.messages.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import vn.com.tma.vo_ngoc_hanh.mychat.R

class RepresentativeMessagesAdapter(var items:List<RepresentativeMessage>) : RecyclerView.Adapter<RepresentativeMessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepresentativeMessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_representative_message, parent, false)

        return RepresentativeMessageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RepresentativeMessageViewHolder, position: Int) {
        holder.bind(items.get(position))
    }
}