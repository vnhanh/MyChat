package vn.com.tma.vo_ngoc_hanh.mychat.base.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.AccountDAO
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.message.Message
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.type_converter.DataConverter

@Database(entities = [Account::class, Message::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE:AppDatabase ?= null

        fun getInstance(context: Context): AppDatabase{
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,AppDatabase::class.java,"Chat Database")
                            .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun accountDAO() : AccountDAO
}