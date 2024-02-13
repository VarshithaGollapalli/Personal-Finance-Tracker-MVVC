package com.capgemini.financetracker.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.capgemini.financetracker.model.FinancialDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FinancialDataViewModel(application: Application): AndroidViewModel(application) {


    //owns the repository and activity owns the view model

    private val repo = FinancialDataRepository(application)

    var isDataAdded = MutableLiveData<Boolean>(false)
    var financialDataList = repo.getFinancialData()
    val currentBalance: LiveData<Double> = repo.currentBalance()
    val totalIncome: LiveData<Double> = repo.totalIncome()
    val totalExpense: LiveData<Double> = repo.totalExpense()
   // var isAdded=MutableLiveData<Boolean>(false)
    fun addData(
        id: Long,
        type: String,
        amount: Double,
        category: String,
        description: String,
        date: String
    ) {

        //launch Coroutine

        CoroutineScope(Dispatchers.Main).launch(Dispatchers.Default) {

            isDataAdded.postValue(repo.addData(id, type, amount, category, description, date))
        }
    }

    fun addUser(name:String,
                email:String,
                password:Int,
                confirmpassword:Int){
        viewModelScope.launch(Dispatchers.Default) {

            isDataAdded.postValue(repo.addUser(name, email, password, confirmpassword))
        }
    }



    fun getUser(email:String,password:Int){
        viewModelScope.launch(Dispatchers.Default){
            repo.getPersonEmailWithException(email,password)
        }

    }
}