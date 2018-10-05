package vn.com.tma.vo_ngoc_hanh.mychat.base.custom_view

import android.content.Context
import android.os.Build
import android.support.v7.app.AlertDialog
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

import vn.com.tma.vo_ngoc_hanh.mychat.R

class DialogCreater {
    companion object {

        fun createProgressDialog(context: Context?, title:String, msg:String, isCancelable:Boolean): AlertDialog? {
            var builder:AlertDialog.Builder?=null

            if (context != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = AlertDialog.Builder(context, 0)
                }else{
                    builder = AlertDialog.Builder(context)
                }

                if (!title.equals("")) {
                    builder.setTitle(title)
                }

                val view = initBody(context, msg)
                val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                view.layoutParams= lp

                builder.setView(view)

                builder.setCancelable(isCancelable)
            }

            return builder?.create()
        }

        private fun initBody(context: Context, msg: String): View {
            val textSize = context.resources.getDimensionPixelSize(R.dimen.textSize_lb_normal)
            val view = CustomProgressView.Builder(context)
                    ._setMessage(msg)._setMessageTextSize(textSize.toFloat())
                    .create()
            return view
        }
    }
}