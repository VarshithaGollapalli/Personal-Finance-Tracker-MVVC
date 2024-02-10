package com.capgemini.financetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capgemini.personalfinanacetracker.model.FinancialDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FinancialDataViewModel(application: Application): AndroidViewModel(application) {


    //owns the repository and activity owns the view model

    private val repo = FinancialDataRepository(application)

    var isDataAdded=MutableLiveData<Boolean>(false)




    fun addData(id:Long,
                type: String,
                amount: Double,
                category: String,
                description: String,
                date: String){

        //launch Coroutine

        CoroutineScope(Dispatchers.Main).launch(Dispatchers.Default) {

            isDataAdded.postValue(repo.addData(id,type, amount, category, description, date))
        }

    }
}