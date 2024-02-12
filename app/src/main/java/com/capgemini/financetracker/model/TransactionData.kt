package com.capgemini.personalfinanacetracker.model

data class TransactionData(
    val type:String,
    val amount: Double,
    val category: String,
    val description: String,
    val date: String,
    val image:Int)