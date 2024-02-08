package com.capgemini.financetracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capgemini.financetracker.R
import com.capgemini.financetracker.databinding.ActivityMainBinding
import com.capgemini.financetracker.databinding.FragmentTransactionHistoryBinding
import com.capgemini.financetracker.model.LogoutDialog
import com.capgemini.financetracker.model.TransactionData
import com.capgemini.financetracker.view.placeholder.PlaceholderContent
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val transcation= mutableListOf<PlaceholderContent.PlaceholderItem>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu_dashboard, R.id.menu_record, R.id.menu_transaction_history, R.id.menu_analysis
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val b= FragmentTransactionHistoryBinding.inflate(layoutInflater)
//        val data = listOf(TransactionData("123","2342","342",411))
//        val adapter = MyItemRecyclerViewAdapter(data)
//        b.rView.adapter = adapter
    }

    fun floatingBtnClick(){
        //inflate
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        menuInflater.inflate(R.menu.menu_transaction,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.sort_income->{
                transcation.filter {
                    it.type == "Income"
                }

            }
            R.id.sort_expense-> {
                transcation.filter {
                    it.type == "Income"
                }
            }
        }
//        when(item.itemId){
////            R.id.menu_transaction_history -> {
////                //inflate
////            }
//            R.id.log_out -> {
//                onBackPressed()
//            }
//        }
        return super.onOptionsItemSelected(item)
    }


    var backCounter = 0
    override fun onBackPressed() {
        backCounter++
        if(backCounter == 2){
            super.onBackPressed()
            backCounter=0
        }
        else{
            val dlg = LogoutDialog()
            dlg.isCancelable = false
            dlg.show(supportFragmentManager, null)

        }

    }


}