package com.capgemini.personalfinanacetracker.model

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FinancialDataRepository(val ctx:Context) {

    val finDao = FinancialDataDatabase.getInstance(ctx).finaceDao()
//    val allExpenses: LiveData<List<FinancialDataEntry>> = finDao.getAllFinancialData()

    suspend fun addData(
        id:Long,
        type: String,
        amount: Double,
        category: String,
        description: String,
        date: String
    ): Boolean {

        var isAdded = false


        try {

            finDao.addData(FinancialDataEntry(id,type, amount, category, description, date))
            isAdded = true

        } catch (err: Exception) {

            isAdded = false

        }



        return isAdded

    }




    suspend fun adduser(name:String,mail:String,password:Int,confirmpassword:Int):Boolean{

        var isAdd=false
        try {
            finDao.addUser(Credentials(name,mail,password, confirmpassword))
            isAdd=true
        }catch (err:Exception){
            isAdd=false
        }
        return isAdd

    }
    fun getPersonEmailWithException(mail:String,pass:Int):Credentials{
        val user=finDao.getUser(mail,pass)
        if(user==null ){
            throw Exception("user not found")
        }
        return user

    }

    suspend fun getperson(mail: String,pass:Int):Credentials?{
        return finDao.getUser(mail,pass)
    }

    fun getFinancialData(): LiveData<List<FinancialDataEntry>> {
        return finDao.getAllFinancialData()
    }

}
