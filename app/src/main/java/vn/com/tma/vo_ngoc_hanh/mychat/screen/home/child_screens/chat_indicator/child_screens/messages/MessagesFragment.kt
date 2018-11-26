package vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.messages


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_messages.*

import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.message.Message
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.surface_account.LocalSurfaceAccount
import vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.messages.recyclerview.RepresentativeMessage
import vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.messages.recyclerview.RepresentativeMessagesAdapter


class MessagesFragment : Fragment() {

    private lateinit var adapter:RepresentativeMessagesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_messages, container, false)

        val list:List<RepresentativeMessage> = createFakeList()
        adapter = RepresentativeMessagesAdapter(list)

        return view
    }

    private fun createFakeList(): List<RepresentativeMessage> {
        val list = ArrayList<RepresentativeMessage>()

        list.add(getFakeLastMessage())
        list.add(getFakeLastMessage())
        list.add(getFakeLastMessage())
        list.add(getFakeLastMessage())

        return list
    }

    private fun getFakeLastMessage() : RepresentativeMessage{
        val lastMessage = RepresentativeMessage()
        lastMessage.account = getFakeLocalAccount()
        lastMessage.message = getFakeMessage()
        return lastMessage
    }

    private fun getFakeMessage() : Message{
        val message = Message(
                "lasc190laslcdq83NAhncfa8921NALALmoiqa",
                "aljsafoiqhfqhOIAOIHW01289LANLll",
                "alsjdlajoq0)124ulasdoj0q0dqjasnk",
                "Hello, I'm Hanh. Nice to meet you",
                0,
                1540955545,
                true
        )

        return message
    }

    private fun getFakeLocalAccount(): LocalSurfaceAccount {
        val account = LocalSurfaceAccount(
                "aljsafoiqhfqhOIAOIHW01289LANLll",
                "Võ Chiến Thắng",
                "https://cdn-b.william-reed.com/var/wrbm_gb_hospitality/storage/images/7/6/2/6/976267-1-eng-GB/Gordon-Ramsay-Group-announces-four-new-appointments_wrbm_large.jpg",
                true,
                "nghiamy14@gmail.com"
        )

        return account
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_messages.setHasFixedSize(true)
    }
}