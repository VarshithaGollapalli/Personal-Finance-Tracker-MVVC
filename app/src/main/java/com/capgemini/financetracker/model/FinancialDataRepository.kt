package com.capgemini.personalfinanacetracker.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map

class FinancialDataRepository(val ctx:Context) {

    val finDao = FinancialDataDatabase.getInstance(ctx).finaceDao()
//    val allExpenses: LiveData<List<FinancialDataEntry>> = finDao.getAllFinancialData()

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


    suspend fun adduser(name: String, mail: String, password: Int, confirmpassword: Int): Boolean {

        var isAdd = false
        try {
            finDao.addUser(Credentials(name, mail, password, confirmpassword))
            isAdd = true
        } catch (err: Exception) {
            isAdd = false
        }
        return isAdd

    }

    fun getPersonEmailWithException(mail: String, pass: Int): Credentials {
        val user = finDao.getUser(mail, pass)
        if (user == null) {
            throw Exception("user not found")
        }
        return user

    }

    suspend fun getperson(mail: String, pass: Int): Credentials? {
        return finDao.getUser(mail, pass)
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
            if (entry.type.lowercase().equals("income")) {
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
            if (entry.type.lowercase().equals("expense")) {
                expense += entry.amount
            }
        }
        return expense
    }
}



//    fun ExpenseAndIncome(): LiveData<Pair<Double,Double>> = finDao.getAllFinancialData().map { calculateExpenseAndIncome(it) }
//    fun calculateExpenseAndIncome(financeList:List<FinancialDataEntry>): Pair<Double, Double> {
//        var totalExpense = 0.0
//        var totalIncome = 0.0
//        financeList.forEach {entry ->
//            if(entry.type.lowercase().equals("expense")) {
//                totalExpense += entry.amount
//            }
//            else{
//                totalIncome += entry.amount
//            }
//        }
//        return Pair(totalExpense,totalIncome)
//
//    }
//
//
//
//}
