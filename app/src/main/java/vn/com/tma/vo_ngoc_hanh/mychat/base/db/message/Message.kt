package vn.com.tma.vo_ngoc_hanh.mychat.base.db.message

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "messages")
data class Message (
        @ColumnInfo(name = "content") var content:String,
        @ColumnInfo(name = "type") var type:Int,
        @ColumnInfo(name = "timestamp") var timeStamp:Long
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long=0
}