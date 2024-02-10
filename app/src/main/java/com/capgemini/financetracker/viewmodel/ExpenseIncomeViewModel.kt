package com.capgemini.financetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.capgemini.financetracker.model.Expense
import com.capgemini.financetracker.model.ExpenseIncomeRepository
import com.capgemini.financetracker.model.Income
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseIncomeViewmodel (application: Application) : AndroidViewModel(application) {
    private val repository = ExpenseIncomeRepository(application)
    val allExpenses: LiveData<List<Expense>> = repository.allExpenses
    val allIncome: LiveData<List<Income>> = repository.allIncome
    fun insertExpense(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertExpense(expense)
    }
    fun insertIncome(income: Income) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertIncome(income)
    }
}