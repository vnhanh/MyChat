package vn.com.tma.vo_ngoc_hanh.mychat.screen.chat


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_chat.*

import vn.com.tma.vo_ngoc_hanh.mychat.R


class ChatFragment : Fragment(), OnBackPressedListener {
    override fun onBackPressed() {
        activity?.onBackPressed()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(R.drawable.gordon_ramsay).apply(RequestOptions.circleCropTransform()).into(menu_logo)
        title_toolbar.setText("Võ Chiến Thắng")
        menu_back.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}