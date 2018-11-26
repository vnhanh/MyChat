package vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.messages.recyclerview

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_representative_message.view.*
import java.util.*

import vn.com.tma.vo_ngoc_hanh.mychat.R

class RepresentativeMessageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var icon: ImageView
    private lateinit var chatterName: TextView
    private lateinit var message: TextView
    private lateinit var time: TextView
    private lateinit var seenIcon: ImageView

    init {
        icon = view.findViewById(R.id.img_icon)
        chatterName = view.findViewById(R.id.tv_chatter_name)
        message = view.findViewById(R.id.tv_message_content)
        time = view.findViewById(R.id.tv_time)
        seenIcon = view.findViewById(R.id.img_check)

        view.setOnClickListener {
            it.findNavController().navigate(R.id.nav_homeToChat)
        }
    }

    fun bind(item: RepresentativeMessage) {
        if (item.account?.iconUrl != null) {
            Glide.with(view).load(item.account?.iconUrl).apply(RequestOptions.circleCropTransform()).into(icon)
        }

        chatterName.setText(item.account?.fullname)
        message.setText(item.message?.content)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = item.message!!.timeStamp
        val createdDate = DateFormat.format("dd/MM/yyyy", calendar).toString()
        time.setText(createdDate)

        Glide.with(view).load(R.drawable.ic_check_16_0).apply(RequestOptions.circleCropTransform()).into(seenIcon)
    }
}