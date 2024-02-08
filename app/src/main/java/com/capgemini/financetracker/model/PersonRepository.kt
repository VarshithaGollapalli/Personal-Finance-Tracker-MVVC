package com.capgemini.financetracker.model

import android.content.Context

class PersonRepository(val ctx: Context) {


    val psonDao=PersonDatabase.getInstance(ctx).getDao()

    suspend fun addperson(name:String,mail:String,password:Int,confirmpassword:Int):Boolean{

        var isAdded=false


        try {

            psonDao.addperson(Person(name,mail,password, confirmpassword))
            isAdded=true

        }catch (err:Exception){

            isAdded=false

        }



        return isAdded

    }

    suspend fun getperson(mail: String,pass:Int):Person?{

        return psonDao.getperson(mail,pass)

    }
}