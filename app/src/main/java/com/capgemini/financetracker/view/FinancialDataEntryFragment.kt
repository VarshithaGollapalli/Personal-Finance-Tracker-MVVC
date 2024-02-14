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
import com.capgemini.financetracker.databinding.FragmentDashboardBinding
import com.capgemini.financetracker.databinding.FragmentFinancialDataEntryBinding
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel
import java.util.*

class FinancialDataEntryFragment: Fragment() {
    lateinit var binding: FragmentFinancialDataEntryBinding

    var isAddedClicked = false
    lateinit var financeVM: FinancialDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentFinancialDataEntryBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dateD.setOnClickListener{
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

        binding.submitB.setOnClickListener {

            isAddedClicked = true

            Log.d("Finance data entry", "second")

            val type: String

            val amount = binding.amountE.text.toString().toDoubleOrNull()

            val category = binding.catE.text.toString()

            val description = binding.descE.text.toString()

            val date = binding.dateD.text.toString()

            type = if (binding.radioGroup.checkedRadioButtonId == R.id.incomeRB) {

                "income"

            } else {

                "expense"

            }

            if (amount != null && amount > 0 && category.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty()) {

                financeVM.addData(0, type, amount, category, description, date)

                Toast.makeText(requireContext(), "Entry Added", Toast.LENGTH_LONG).show()

                // Clearing input fields after successful addition

                binding.radioGroup.clearCheck()

                binding.dateD.text.clear()

                binding.amountE.text.clear()

                binding.catE.text.clear()

                binding.descE.text.clear()

            } else {

                Toast.makeText(requireContext(), "Please fill all fields correctly", Toast.LENGTH_LONG).show()

            }

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
                binding.dateD.setText(sdate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
