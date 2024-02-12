package com.capgemini.personalfinanacetracker.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map

class FinancialDataRepository(ctx:Context) {

    val finDao = FinancialDataDatabase.getInstance(ctx).finaceDao()

    suspend fun addData(
        id: Long,
        type: String,
        amount: Double,
        category: String,
        description: String,
        date: String

    ): Boolean {

        var isAdded = false


        try {

            finDao.addData(FinancialDataEntry(id, type, amount, category, description, date))
            isAdded = true

        } catch (err: Exception) {

            isAdded = false

        }



        return isAdded

    }


    fun getPersonEmailWithException(mail: String, pass: Int): Credentials {
        val user = finDao.getUser(mail, pass)
        if (user == null) {
            throw Exception("user not found")
        }
        return user

    }


    fun getFinancialData(): LiveData<List<FinancialDataEntry>> {
        return finDao.getAllFinancialData()
    }

    fun currentBalance(): LiveData<Double> =
        finDao.getAllFinancialData().map { calculateCurrentBalance(it) }

    private fun calculateCurrentBalance(financeList: List<FinancialDataEntry>): Double {
        var balance = 0.0
        financeList.forEach { entry ->
            when (entry.type.lowercase()) {
                "income" -> balance += entry.amount
                "expense" -> balance -= entry.amount
            }

        }
        return balance
    }

    fun totalIncome(): LiveData<Double> =
        finDao.getAllFinancialData().map { calculateTotalIncome(it) }

    private fun calculateTotalIncome(financeList: List<FinancialDataEntry>): Double {
        var income = 0.0
        var expense = 0.0
        financeList.forEach { entry ->
            if (entry.type.equals("income")) {
                income += entry.amount
            }
        }
        return income
    }

    fun totalExpense(): LiveData<Double> =
        finDao.getAllFinancialData().map { calculateTotalExpense(it) }

    private fun calculateTotalExpense(financeList: List<FinancialDataEntry>): Double {
        var expense = 0.0
        financeList.forEach { entry ->
            if (entry.type.equals("expense")) {
                expense += entry.amount
            }
        }
        return expense
    }
}

