package vn.com.tma.vo_ngoc_hanh.mychat.screen.home

import android.support.v4.view.ViewPager
import android.view.View

class FadePageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.translationX = page.width *- position

        if(position <= -1.0f || position >= 1.0f){
            page.alpha = 0.0f
            page.visibility = View.GONE
        }else if(position == 0.0f){
            page.alpha = 1.0f
            page.visibility = View.VISIBLE
        }else{
            page.alpha = 1.0f - Math.abs(position)
            page.visibility = View.VISIBLE
        }
    }
}