package vn.com.tma.vo_ngoc_hanh.mychat.base.custom_view

import android.content.Context
import android.os.Build
import android.support.v7.app.AlertDialog

class DialogCreater {
    companion object {
        fun create(context: Context?, title:String, msg:String, isCancelable:Boolean): AlertDialog? {
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

                if (!msg.equals("")) {
                    builder.setMessage(msg)
                }

                builder.setCancelable(isCancelable)
            }

            return builder?.create()
        }
    }
}