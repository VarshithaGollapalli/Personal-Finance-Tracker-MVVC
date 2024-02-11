package com.capgemini.financetracker.view

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capgemini.financetracker.R
import com.capgemini.financetracker.databinding.FragmentDashboardBinding
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel


class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding
    private lateinit var financeVM: FinancialDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDashboardBinding.inflate(layoutInflater)

//        val mHost = requireActivity()
//        mHost.addMenuProvider(object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                if(menu.findItem(R.id.sort_expense)!= null || menu.findItem(R.id.sort_income)!= null) {
//
//                    menu.findItem(R.id.sort_income)?.isVisible = false
//                    menu.findItem(R.id.sort_expense)?.isVisible = false
//                }
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                return false
//            }
//
//        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentDashboardBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        financeVM = ViewModelProvider(this).get(FinancialDataViewModel::class.java)

        financeVM.currentBalance.observe(viewLifecycleOwner, Observer { balance ->
            binding.totalamtT.text = "Current Balance $balance"
        })

        financeVM.totalIncome.observe(viewLifecycleOwner, Observer { income ->
            binding.incomeT.text = "Total Income: $income"
        })

        financeVM.totalExpense.observe(viewLifecycleOwner, Observer { expense ->
            binding.expenseT.text = "Total Expense: $expense"
        })

    }

}