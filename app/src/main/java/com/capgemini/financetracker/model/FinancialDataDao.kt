package com.capgemini.personalfinanacetracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FinancialDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(dataentry:FinancialDataEntry)

    @Insert
    fun addUser(pson:Credentials)

    @Query("select * from credentials where email=:mail and password=:pass")
    fun getUser(mail:String,pass:Int):Credentials?


}