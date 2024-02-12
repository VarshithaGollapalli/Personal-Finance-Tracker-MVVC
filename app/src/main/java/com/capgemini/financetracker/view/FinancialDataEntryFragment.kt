package com.capgemini.financetracker.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capgemini.financetracker.R
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel
import java.util.*

class FinancialDataEntryFragment: Fragment() {
    lateinit var amountEditText: EditText
    lateinit var categoryEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var submitButton: Button
    lateinit var radioGroupType: RadioGroup
    lateinit var radioButtonIncome: RadioButton
    lateinit var radioButtonExpense: RadioButton

    lateinit var dateEditText: EditText

    var isAddedClicked = false
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
        descriptionEditText = view.findViewById(R.id.descE)
        dateEditText= view.findViewById(R.id.dateD)
        submitButton=view.findViewById(R.id.submitB)
        radioButtonIncome = view.findViewById(R.id.incomeRB)
        radioButtonExpense = view.findViewById(R.id.expenseRB)
        radioGroupType=view.findViewById(R.id.radioGroup)
        Log.d("Financial data entry","first")

        dateEditText.setOnClickListener{
            datePick()
        }

        financeVM = ViewModelProvider(this).get(FinancialDataViewModel::class.java)

        financeVM.isDataAdded.observe(requireActivity()) {
            if (isAddedClicked) {

                if (it) {
                    Toast.makeText(requireContext(), "Entry Added", Toast.LENGTH_LONG)
                        .show()

                }

            }
        }


        submitButton.setOnClickListener {
            isAddedClicked = true
            Log.d("Finance data entry", "second")
            val type = ""
            val amount = amountEditText.text.toString().toDoubleOrNull()
            val category = categoryEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val date = dateEditText.text.toString()
            if (radioGroupType.checkedRadioButtonId==R.id.incomeRB){

                Log.d("FinancialDataEntryFragment","second")

                if (amount != null) {
                    financeVM.addData(id=0,type = "income",amount,category,description,date)
                }

            }
            else{

                Log.d("FinancialDataEntryFragment","third")
                type=="expense"
                if (amount != null) {
                    financeVM.addData(id=0,type = "expense",amount,category,description,date)
                }

            }
            if(type.isNotEmpty() && amountEditText.text.isNotEmpty() && category.isNotEmpty()
                && description.isNotEmpty() && date.isNotEmpty()){
                if (amount != null) {
                    financeVM.addData(id = 0, type, amount, category, description, date)
                }
            }
                else{
                    Toast.makeText(requireContext(),"Enter all fields",Toast.LENGTH_LONG).show()
                }
            radioGroupType.clearCheck()
            dateEditText.text.clear()
            amountEditText.text.clear()
            categoryEditText.text.clear()
            descriptionEditText.text.clear()
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
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
