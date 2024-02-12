package com.capgemini.financetracker.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.financetracker.R
import com.capgemini.financetracker.R.id.imageView
import com.capgemini.financetracker.view.placeholder.PlaceholderContent
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel
import com.capgemini.personalfinanacetracker.model.FinancialDataEntry

/**
 * A fragment representing a list of Items.
 */
class TransactionHistoryFragment : Fragment() {

    //val transcation= mutableListOf<FinancialDataEntry>()


    private var columnCount = 1
    //    private var isMenuProvidedAdded=false
//    lateinit var customMenu : Menu
    lateinit var recyclerView: RecyclerView
    lateinit var financeVM: FinancialDataViewModel
    lateinit var sortByIncomeButton: Button
    lateinit var sortByExpenseButton: Button
    lateinit var imageView: ImageView
    //private lateinit var financeDataCass:FinancialDataEntry

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("customtrans", "onCreate")
        super.onCreate(savedInstanceState)

//        if (!isMenuProvidedAdded) {
//            val mHost = requireActivity()
//            mHost.addMenuProvider(object : MenuProvider {
//                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                    //if (menu.findItem(R.id.sort_income) == null) {
//                    customMenu = menu
//                    menuInflater.inflate(R.menu.menu_transaction, menu)
//                    Log.d("provider", "add")
//                    isMenuProvidedAdded = true
//
//                }
//
//                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                    when (menuItem.itemId) {
//                        R.id.sort_income -> {
//                            transcation.filter {
//                                it.type == "Income"
//                            }
//
//                        }
//                        R.id.sort_expense -> {
//                            transcation.filter {
//                                it.type == "Income"
//                            }
//                        }
//                    }
//                    return true
//                }
//            })
//
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("customtrans", "onCreateView")
        val view = inflater.inflate(R.layout.fragment_transaction_history_list, container, false)

        // Set the adapter
//        recyclerView?.apply {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(requireContext())
//                    else -> GridLayoutManager(context, columnCount)
//                }
//                adapter = TransactionHistoryAdapter(FinancialDataEntry) //red mark
//            }
        recyclerView = view.findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        financeVM=ViewModelProvider(this).get(FinancialDataViewModel::class.java)
        financeVM.financialDataList.observe(this){
            recyclerView.adapter=TransactionHistoryAdapter(it)
        }
//        val type = financeDataCass.type.lowercase()
//
////        imageView = view.findViewById(R.id.imageView)
//        if(type.equals("income")){
//            imageView.setImageResource(R.drawable.income)
//        }else{
//            imageView.setImageResource(R.drawable.expense)
//        }

        sortByIncomeButton = view.findViewById(R.id.sIncB)
        sortByIncomeButton.setOnClickListener {
            Log.d("sort","entered")
            val sortedData = financeVM.financialDataList.value?.filter { it.type == "income" || it.type =="Income"}
            recyclerView.adapter = TransactionHistoryAdapter(sortedData.orEmpty())
        }
        sortByExpenseButton = view.findViewById(R.id.sExpB)
        sortByExpenseButton.setOnClickListener {
            Log.d("sort","entered")
            val sortedData = financeVM.financialDataList.value?.filter { it.type == "expense" || it.type =="Expense"}
            recyclerView.adapter = TransactionHistoryAdapter(sortedData.orEmpty())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("customtrans", "onViewCreated")

    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        requireActivity().invalidateOptionsMenu()
//        Log.d("customtranscu", "onDestroy")
//        customMenu.clear()
//        customMenu.removeItem(R.id.sort_income)
//        customMenu.removeItem(R.id.sort_expense)
//    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TransactionHistoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

}