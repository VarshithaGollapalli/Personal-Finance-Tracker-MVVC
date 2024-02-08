package com.capgemini.financetracker.model

data class TransactionData(
    val type: String,
    val category: String,
    val description: String,
    val amount: Int)