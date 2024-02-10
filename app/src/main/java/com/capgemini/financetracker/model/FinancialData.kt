package com.capgemini.personalfinanacetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FinancialDataEntry(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val type:String,
    val amount: Double,
    val category: String,
    val description: String,
    val date: String
    )

@Entity
data class Credentials(
    val name:String,
    @PrimaryKey val email:String,
    var password:Int,
    var confirmpassword:Int
)
