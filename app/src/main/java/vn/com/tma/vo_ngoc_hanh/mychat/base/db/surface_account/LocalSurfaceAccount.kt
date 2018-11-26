package vn.com.tma.vo_ngoc_hanh.mychat.base.db.surface_account

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/*
* A surface account is a simplify account. It only contains public attributes to keep security
* */

@Entity(tableName = "surface_accounts")
data class LocalSurfaceAccount(
        @PrimaryKey
        @ColumnInfo(name = "uid") var uid:String="",
        @ColumnInfo(name = "fullname") var fullname:String,
        @ColumnInfo(name = "icon_url") var iconUrl:String,
        @ColumnInfo(name = "gender") var gender:Boolean,
        @ColumnInfo(name = "email") var email:String
)