package com.capgemini.financetracker.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capgemini.personalfinanacetracker.model.Credentials
import com.capgemini.personalfinanacetracker.model.FinancialDataDao
import com.capgemini.personalfinanacetracker.model.FinancialDataEntry

@Database(entities = [ FinancialDataEntry::class, Credentials::class], version = 2, exportSchema =true)
abstract class FinancialDataDatabase : RoomDatabase() {
    abstract fun financeDao(): FinancialDataDao
    companion object{
        private var instance: FinancialDataDatabase?=null
        fun getInstance(ctx: Context): FinancialDataDatabase {
            //bulidDB function related with viewmodel

            return instance ?: buildDB(ctx).also {
                instance = it
            }
        }
        private fun buildDB(ctx: Context): FinancialDataDatabase {
            return Room.databaseBuilder(ctx.applicationContext,
                FinancialDataDatabase::class.java,
                "financialRecord.db").build()
        }
    }
}