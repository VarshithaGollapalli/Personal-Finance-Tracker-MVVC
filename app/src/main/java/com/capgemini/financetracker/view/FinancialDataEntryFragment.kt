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
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class FinancialDataEntryFragment: Fragment() {
    lateinit var amountEditText: EditText
    lateinit var categoryEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var typeEditText: EditText
    lateinit var incomeB: Button
    lateinit var expenseB: Button
    lateinit var dateEditText: EditText

    var isAddedClicked = false
    //private lateinit var repository: ExpenseIncomeRepository
    lateinit var financeVM: FinancialDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_financial_data_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        amountEditText = view.findViewById(R.id.amountE)
        categoryEditText = view.findViewById(R.id.catE)
        typeEditText=view.findViewById(R.id.typeE)
        descriptionEditText = view.findViewById(R.id.descE)
        dateEditText= view.findViewById(R.id.dateD)
        incomeB=view.findViewById(R.id.incomeB)
        //expenseB=view.findViewById(R.id.expenseB)
        Log.d("ExpenseIncomeentryyyy","first")

        dateEditText.setOnClickListener{
            datePick()
        }

        financeVM = ViewModelProvider(this).get(FinancialDataViewModel::class.java)

        financeVM.isDataAdded.observe(requireActivity()) {
            if (isAddedClicked) {

                if (it) {
                    Toast.makeText(requireContext(), "Entry Added", Toast.LENGTH_LONG)
                        .show()
                    //finish()
                }

            }
        }


        incomeB.setOnClickListener {
            isAddedClicked = true
            Log.d("ExpenseIncomeentryyyy", "second")
            val type=typeEditText.text.toString()
            val amount = amountEditText.text.toString().toDouble()
            val category = categoryEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val date=dateEditText.text.toString()


            val result = financeVM.addData(id=0,type,amount,category,description,date)




        }
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
                dateEditText.setText(sdate)
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
