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
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val frag=ExpenseIncomeEntryyy()
//        val transaction=supportFragmentManager.beginTransaction()
//        transaction.add(R.id.nav_host_fragment,frag)
//
//        transaction.commit()



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