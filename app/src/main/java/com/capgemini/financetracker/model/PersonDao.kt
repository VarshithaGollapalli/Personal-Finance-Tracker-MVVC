package com.capgemini.financetracker.model

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PersonDao {

    @Insert
    fun addperson(pson:Person)

    @Query("select * from person where email=:mail and password=:pass")
    fun getperson(mail:String,pass:Int):Person?

    fun getpersonemailwithexception(mail:String,pass:Int):Person{
        val user=getperson(mail,pass)
        if(user==null ){
            throw Exception("user not found")
        }
        return user

    }
}