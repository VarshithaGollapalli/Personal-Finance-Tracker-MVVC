package com.capgemini.personalfinanacetracker.model

data class TransactionData(
    val type: String,
    val category: String,
    val description: String,
    val amount: Int)