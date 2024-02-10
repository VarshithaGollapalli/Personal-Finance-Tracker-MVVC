package com.capgemini.financetracker.view

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capgemini.financetracker.R
import com.capgemini.financetracker.model.Expense
import com.capgemini.financetracker.model.Income
import com.capgemini.financetracker.model.TransactionDatabase
import com.capgemini.financetracker.viewmodel.ExpenseIncomeViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class ExpenseIncomeEntryyy: Fragment() {
    lateinit var amountEditText: EditText
    lateinit var categoryEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var incomeB: Button
    lateinit var expenseB: Button
    lateinit var date: EditText

    var isAddedClicked = false
    //private lateinit var repository: ExpenseIncomeRepository
    lateinit var expenseIncomeVM: ExpenseIncomeViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_income_entryyy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseIncomeVM = ViewModelProvider(requireActivity()).get(ExpenseIncomeViewmodel::class.java)
        amountEditText = view.findViewById(R.id.amountE)
        categoryEditText = view.findViewById(R.id.catE)
        descriptionEditText = view.findViewById(R.id.descE)
        date= view.findViewById(R.id.dateD)
        incomeB=view.findViewById(R.id.incomeB)
        expenseB=view.findViewById(R.id.expenseB)
        Log.d("ExpenseIncomeentryyyy","first")

        date.setOnClickListener{
            datePick()
        }

        incomeB.setOnClickListener {
            isAddedClicked = true
            Log.d("ExpenseIncomeentryyyy","second")
            val amount = amountEditText.text.toString().toDouble()
            val category = categoryEditText.text.toString()
            val description = descriptionEditText.text.toString()

            CoroutineScope(Dispatchers.Default).launch {
                val financedao = TransactionDatabase.getInstance(requireContext()).transactionDao()
                val income = Income(
                    amount = amount,
                    category = category,
                    description = description,
                    date = date.text.toString()
                )

                expenseIncomeVM.insertIncome(income)
                CoroutineScope(Dispatchers.Main).launch{
                    if (isAddedClicked){
                        Toast.makeText(requireContext(),"income saved",Toast.LENGTH_LONG)
                    }
                    else{
                        Toast.makeText(requireContext(),"income not saved",Toast.LENGTH_LONG)
                    }
                }
            }
            expenseB.setOnClickListener {
                val amount = amountEditText.text.toString().toDouble()
                val category = categoryEditText.text.toString()
                val description = descriptionEditText.text.toString()
                val expense = Expense(
                    amount = amount,
                    category = category,
                    description = description,
                    date = date.text.toString()
                )
                expenseIncomeVM.insertExpense(expense)

            }
        }
        expenseIncomeVM = ViewModelProvider(this).get(expenseIncomeVM::class.java)
    }
    fun datePick(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(requireContext(),
            { view: DatePicker, year:Int, monthOfYear:Int, dayOfMonth:Int ->
                // setting date to our edit text.
                val sdate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                date.setText(sdate)
//                    val sDate= Calendar.getInstance()
//                    sDate.set(year,monthOfYear,dayOfMonth)
//                    val dateFormat= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//                    val formattedDate=dateFormat.format(sDate.time)
//                    date.setText(formattedDate)
            },
            //passing year, month and day for the selected date in our date picker.
            year,
            month,
            day
        )
        datePickerDialog.show()
    }


}
