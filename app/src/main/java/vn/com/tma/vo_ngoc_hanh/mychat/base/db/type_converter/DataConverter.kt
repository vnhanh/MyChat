package vn.com.tma.vo_ngoc_hanh.mychat.base.db.type_converter

import android.arch.persistence.room.TypeConverter
import vn.com.tma.vo_ngoc_hanh.mychat.common.util.Util
import java.text.SimpleDateFormat
import java.util.*

class DataConverter {
    @TypeConverter
    fun timeStampToDate(timestamp:Long) : Date{
        return Util.timeStampToDate(timestamp, "dd-MM-yyyy hh:mm")
    }

    @TypeConverter
    fun dateToTimeStamp(date:Date) : Long{
        return date.time
    }
}