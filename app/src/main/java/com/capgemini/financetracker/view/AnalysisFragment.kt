package com.capgemini.financetracker.view

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capgemini.financetracker.R
import com.capgemini.financetracker.databinding.FragmentDashboardBinding
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel

class AnalysisFragment : Fragment() {
    private lateinit var financialDataViewModel:FinancialDataViewModel
    private lateinit var txtExpense: TextView
    private lateinit var txtIncome: TextView
    private lateinit var expenseProgressBar: ProgressBar
    private lateinit var incomeProgressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_analysis, container, false)
        txtExpense = view.findViewById(R.id.textExpense)
        txtIncome = view.findViewById(R.id.textIncome)
        expenseProgressBar = view.findViewById(R.id.expenseBar)
        incomeProgressBar = view.findViewById(R.id.incomeBar)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var totalIncome=Double.MIN_VALUE
        var totalExpense=Double.MIN_VALUE
        financialDataViewModel = ViewModelProvider(this).get(FinancialDataViewModel::class.java)
        financialDataViewModel.totalIncome.observe(viewLifecycleOwner, Observer { income ->
            totalIncome = income
            updateProgressBars(totalExpense,totalIncome)
        })
        financialDataViewModel.totalExpense.observe(viewLifecycleOwner, Observer { expense ->
            totalExpense = expense
            updateProgressBars(totalExpense,totalIncome)
        })
// updateProgressBars(totalExpense,totalIncome)
//
// financialDataViewModel.totalExpense.observe(viewLifecycleOwner, Observer { expense ->
// binding.expenseT.text = "Total Expense: $expense"
// })
// financialDataViewModel.ExpenseAndIncome.observe(viewLifecycleOwner) { transactions ->
// val (totalExpense, totalIncome) = financialDataViewModel.ExpenseAndIncome(transactions)
// }

    }
    private fun updateProgressBars(totalExpense: Double, totalIncome: Double) {
        val maxProgress = (totalExpense + totalIncome).toInt()
        expenseProgressBar.max = maxProgress
        incomeProgressBar.max = maxProgress
        expenseProgressBar.progress = totalExpense.toInt()
        incomeProgressBar.progress = totalIncome.toInt()
    }
}