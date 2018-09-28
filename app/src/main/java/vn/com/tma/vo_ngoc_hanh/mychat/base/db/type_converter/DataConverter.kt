package vn.com.tma.vo_ngoc_hanh.mychat.base.db.type_converter

import android.arch.persistence.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DataConverter {
    @TypeConverter
    fun timeStampToDate(timestamp:Long) : Date{
        val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault())
        val date = Date(timestamp)
        return date
    }

    @TypeConverter
    fun dateToTimeStamp(date:Date) : Long{
        return date.time
    }
}