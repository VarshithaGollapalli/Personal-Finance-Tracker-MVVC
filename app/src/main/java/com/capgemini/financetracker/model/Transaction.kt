package com.capgemini.financetracker.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "Expense")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Double,
    val category: String,
    val description: String,
    val date: String
)

@Entity(tableName = "Income")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Double,
    val category: String,
    val description: String,
    val date: String
)

@Entity(tableName = "Credentials")
data class Credentials(
    val name:String,
    @PrimaryKey val email:String,
    var password:Int,
    var confirmpassword:Int
)