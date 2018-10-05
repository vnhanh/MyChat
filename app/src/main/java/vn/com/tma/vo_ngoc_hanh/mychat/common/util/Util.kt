package vn.com.tma.vo_ngoc_hanh.mychat.common.util

import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        fun timeStampToDate(timestamp:Long, pattern:String) : Date {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            val date = Date(timestamp)
            return date
        }
    }
}