package com.capgemini.financetracker.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpense(expense: Expense)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIncome(income: Income)

    @Query("select * from Expense")
    fun getAllExpense():LiveData<List<Expense>>

    @Query("select * from Income")
    fun getAllIncome():LiveData<List<Income>>

    @Insert
    fun addUser(pson:Credentials)

    @Query("select * from credentials where email=:mail and password=:pass")
    fun getUser(mail:String,pass:Int):Credentials?

}