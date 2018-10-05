package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "accounts")
data class AccountLocal(
        @ColumnInfo(name = "fullname") var fullname:String,
        @ColumnInfo(name = "gender") var gender:Boolean,
        @ColumnInfo(name = "birth_date") var birthDate:Date,
        @ColumnInfo(name = "email") var email:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long=0

    @ColumnInfo(name = "uid") var uid:String=""
}