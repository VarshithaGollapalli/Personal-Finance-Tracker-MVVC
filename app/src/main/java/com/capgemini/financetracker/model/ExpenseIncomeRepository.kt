package com.capgemini.financetracker.model

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData

class ExpenseIncomeRepository(ctx:Context) {
    val expenseIncomeDao = TransactionDatabase.getInstance(ctx).transactionDao()
    val allExpenses: LiveData<List<Expense>> = expenseIncomeDao.getAllExpense()
    val allIncome: LiveData<List<Income>> = expenseIncomeDao.getAllIncome()
    suspend fun insertExpense(expense: Expense) {
        expenseIncomeDao.insertExpense(expense)
    }
    suspend fun insertIncome(income: Income) {
        expenseIncomeDao.insertIncome(income)
    }



    suspend fun adduser(name:String,mail:String,password:Int,confirmpassword:Int):Boolean{

        var isAdded=false
        try {
            expenseIncomeDao.addUser(Credentials(name,mail,password, confirmpassword))
            isAdded=true
        }catch (err:Exception){
            isAdded=false
        }
        return isAdded

    }
    fun getPersonEmailWithException(mail:String,pass:Int):Credentials{
        val user=expenseIncomeDao.getUser(mail,pass)
        if(user==null ){
            throw Exception("user not found")
        }
        return user

    }

    suspend fun getperson(mail: String,pass:Int):Credentials?{
        return expenseIncomeDao.getUser(mail,pass)
    }
}
