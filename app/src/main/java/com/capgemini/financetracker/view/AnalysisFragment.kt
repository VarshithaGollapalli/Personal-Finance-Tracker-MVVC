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
import com.capgemini.financetracker.R.*
import com.capgemini.financetracker.databinding.FragmentAnalysisBinding
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel

class AnalysisFragment : Fragment() {
    private lateinit var financialDataViewModel:FinancialDataViewModel
    lateinit var binding: FragmentAnalysisBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentAnalysisBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
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


    }
    private fun updateProgressBars(totalExpense: Double, totalIncome: Double) {
        val maxProgress = (totalExpense + totalIncome).toInt()
        binding.expenseBar.max = maxProgress
        binding.incomeBar.max = maxProgress
        binding.expenseBar.progress = totalExpense.toInt()
        binding.incomeBar.progress = totalIncome.toInt()
        val percentExpense = (totalExpense / maxProgress) * 100
        val percentIncome = (totalIncome / maxProgress) * 100
        binding.percentageE.text = "${String.format("%.2f", percentExpense)}%"
        binding.percentageI.text = "${String.format("%.2f", percentIncome)}%"
    }
}