package vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.chat_groups


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import vn.com.tma.vo_ngoc_hanh.mychat.R

class ChatGroupsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_groups, container, false)
    }


}
