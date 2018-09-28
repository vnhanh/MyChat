package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface AccountDAO {
    @Query("SELECT * FROM accounts")
    fun getAllAccounts() : LiveData<List<Account>>

    @Query("SELECT * FROM accounts where id = :id")
    fun getAccountById(id:Int): LiveData<Account>

    @Query("SELECT * FROM accounts where email = :email")
    fun getAccountByEmail(email:String): LiveData<Account>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAccount(vararg account: Account)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAccount(vararg accounts: Account)

    @Delete
    fun deleteAccount(account: Account)

    @Query("DELETE FROM accounts")
    fun deleteAllAccounts()
}