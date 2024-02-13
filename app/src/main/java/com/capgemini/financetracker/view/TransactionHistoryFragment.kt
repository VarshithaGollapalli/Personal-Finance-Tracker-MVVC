package com.capgemini.financetracker.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.financetracker.R
import com.capgemini.financetracker.R.id.imageView
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel

class TransactionHistoryFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var financeVM: FinancialDataViewModel
    lateinit var sortByIncomeButton: Button
    lateinit var sortByExpenseButton: Button
    lateinit var entireDataButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_history_list, container, false)

        recyclerView = view.findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        financeVM=ViewModelProvider(this).get(FinancialDataViewModel::class.java)
        financeVM.financialDataList.observe(this){
            recyclerView.adapter=TransactionHistoryAdapter(it)
        }
        sortByIncomeButton = view.findViewById(R.id.sIncB)
        sortByIncomeButton.setOnClickListener {
            Log.d("sort","entered")
            val sortedData = financeVM.financialDataList.value?.filter { it.type == "income" }
            recyclerView.adapter = TransactionHistoryAdapter(sortedData.orEmpty())
        }
        sortByExpenseButton = view.findViewById(R.id.sExpB)
        sortByExpenseButton.setOnClickListener {
            Log.d("sort","entered")
            val sortedData = financeVM.financialDataList.value?.filter { it.type == "expense"}
            recyclerView.adapter = TransactionHistoryAdapter(sortedData.orEmpty())
        }
        entireDataButton = view.findViewById(R.id.allB)
        entireDataButton.setOnClickListener {
            Log.d("sort","entered")
            val sortedData = financeVM.financialDataList.value?.filter { it.type == "expense" || it.type =="income"}
            recyclerView.adapter = TransactionHistoryAdapter(sortedData.orEmpty())
        }

        return view
    }

}