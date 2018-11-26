package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface AccountDAO {
    @Query("SELECT * FROM accounts")
    fun getAllAccounts() : LiveData<List<AccountLocal>>

    @Query("SELECT * FROM accounts where uid = :uid")
    fun getAccountById(uid:String): LiveData<AccountLocal>

    @Query("SELECT * FROM accounts where email = :email")
    fun getAccountByEmail(email:String): LiveData<AccountLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAccount(vararg account: AccountLocal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAccount(vararg accounts: AccountLocal)

    @Delete
    fun deleteAccount(account: AccountLocal)

    @Query("DELETE FROM accounts")
    fun deleteAllAccounts()
}