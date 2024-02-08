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
import com.capgemini.financetracker.model.LogoutDialog
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
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
    }

    fun floatingBtnClick(){
        //inflate
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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