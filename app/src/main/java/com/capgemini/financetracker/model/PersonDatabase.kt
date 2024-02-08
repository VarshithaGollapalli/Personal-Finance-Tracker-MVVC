package com.capgemini.financetracker.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase: RoomDatabase() {

    abstract fun getDao():PersonDao

    companion object{
        private var instance : PersonDatabase? = null


        fun getInstance(ctx: Context):PersonDatabase{
            return instance ?:buildDB(ctx).also {
                instance=it
            }
        }

        private fun buildDB(ctx: Context): PersonDatabase {

            return Room.databaseBuilder(ctx.applicationContext,
                PersonDatabase::class.java,
                "person.db").build()

        }


    }
}