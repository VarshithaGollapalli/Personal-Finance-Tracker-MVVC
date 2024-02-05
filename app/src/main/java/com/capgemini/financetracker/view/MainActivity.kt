package com.capgemini.financetracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.capgemini.financetracker.R
import com.capgemini.financetracker.model.LogoutDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var totalAmountTextView: TextView
    lateinit var incomeTextView: TextView
    lateinit var expenseTextView: TextView
    lateinit var addDetailsFAButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totalAmountTextView = findViewById(R.id.totalamtT)
        incomeTextView = findViewById(R.id.incomeT)
        expenseTextView = findViewById(R.id.expenseT)
        addDetailsFAButton = findViewById(R.id.addFB)
    }

    fun floatingBtnClick(){
        //inflate
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.transaction_history -> {
                //inflate
            }
            R.id.log_out -> {
                onBackPressed()
            }
        }
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