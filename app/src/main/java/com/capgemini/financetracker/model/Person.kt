package com.capgemini.financetracker.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class Person(
    val name:String,
    @PrimaryKey val email:String,
    var password:Int,
    var confirmpassword:Int
)