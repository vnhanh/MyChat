package vn.com.tma.vo_ngoc_hanh.mychat.common.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class CustomFragmentPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    var hasTitle:Boolean = true

    fun addItem(title: String, fragment: Fragment) {
        titles.add(title)
        fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if(hasTitle) titles.get(position) else null
    }

    override fun getCount(): Int {
        return fragments.size
    }
}