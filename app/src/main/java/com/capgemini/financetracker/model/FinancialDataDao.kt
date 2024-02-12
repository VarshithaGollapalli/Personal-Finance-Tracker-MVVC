package com.capgemini.personalfinanacetracker.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FinancialDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(dataEntry:FinancialDataEntry)

    @Insert
    fun addUser(pSon:Credentials)

    @Query("select * from credentials where email=:mail and password=:pass")
    fun getUser(mail:String,pass:Int):Credentials?

    @Query("select * from FinancialDataEntry")
    fun getAllFinancialData(): LiveData<List<FinancialDataEntry>>

}