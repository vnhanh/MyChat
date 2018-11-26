package vn.com.tma.vo_ngoc_hanh.mychat.base.db.message

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "messages")
data class Message (
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id:String,
        @ColumnInfo(name = "uid_sender")
        var senderUID:String,
        @ColumnInfo(name = "uid_receiver")
        var receiverUID:String,
        @ColumnInfo(name = "content") var content:String,
        @ColumnInfo(name = "type") var type:Int,
        @ColumnInfo(name = "timestamp") var timeStamp:Long,
        @ColumnInfo(name = "seen") var seen:Boolean
)