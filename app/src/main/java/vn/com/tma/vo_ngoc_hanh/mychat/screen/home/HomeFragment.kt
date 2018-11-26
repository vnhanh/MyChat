package vn.com.tma.vo_ngoc_hanh.mychat.screen.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.common.adapter.CustomFragmentPagerAdapter
import vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.chat_indicator.ChatIndicatorFragment
import vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.photograph.PhotographFragment
import vn.com.tma.vo_ngoc_hanh.mychat.screen.home.child_screens.setting.SettingFragment

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private val tabIcons:Array<Int> = arrayOf(R.drawable.ic_home_24_0, R.drawable.ic_photograph_24_0, R.drawable.ic_account_24_0)
    private val SELECTED_ALPHA = 255
    private val UNSELECTED_ALPHA = 192

    private fun setupViewPager() {
        val adapter = CustomFragmentPagerAdapter(childFragmentManager)
        adapter.hasTitle = false
        adapter.addItem(getString(R.string.title_menu_home), ChatIndicatorFragment.newInstance())
        adapter.addItem(getString(R.string.title_menu_photograph), PhotographFragment())
        adapter.addItem(getString(R.string.title_menu_setting), SettingFragment())

        vp_home_container.adapter = adapter

        vp_home_container.setPageTransformer(false, FadePageTransformer())

        nav_bottom_home.setupWithViewPager(vp_home_container)
        nav_bottom_home.getTabAt(0)?.setIcon(tabIcons[0])
        nav_bottom_home.getTabAt(1)?.setIcon(tabIcons[1])
        nav_bottom_home.getTabAt(2)?.setIcon(tabIcons[2])

        vp_home_container.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                changeAlphaForTabIcon(position)
            }

        })

        changeAlphaForTabIcon(0)
    }

    private fun changeAlphaForTabIcon(position: Int) {
        for (i in 0..(tabIcons.size)){
            if (i == position) {
                nav_bottom_home.getTabAt(i)?.icon?.alpha = SELECTED_ALPHA
            }else{
                nav_bottom_home.getTabAt(i)?.icon?.alpha = UNSELECTED_ALPHA
            }
        }
    }
}