package vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_chat_indicator.*
import kotlinx.android.synthetic.main.fragment_home.*

import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.common.adapter.CustomFragmentPagerAdapter
import vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.active_users.ActiveUsersFragment
import vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.chat_groups.ChatGroupsFragment
import vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.child_screens.messages.MessagesFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class ChatIndicatorFragment : Fragment() {

    companion object {
        fun newInstance() : ChatIndicatorFragment {
            return ChatIndicatorFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_indicator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCustomSearchView()
        setupViewPager()
    }

    private fun setupCustomSearchView() {
        search_view_custom.inputView.setOnClickListener {

        }
    }

    private fun setupViewPager() {
        if (fragmentManager != null) {
            val adapter = CustomFragmentPagerAdapter(fragmentManager!!)
            adapter.addItem(getString(R.string.title_tab_messages), MessagesFragment())
            adapter.addItem(getString(R.string.title_tab_active_users), ActiveUsersFragment())
            adapter.addItem(getString(R.string.title_tab_chat_groups), ChatGroupsFragment())
            vp_chat.adapter = adapter

            tabs_chat_indicator.setupWithViewPager(vp_chat)
        }
    }
}