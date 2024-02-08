package com.capgemini.financetracker.model

import android.view.Menu
import android.view.MenuInflater
import com.capgemini.financetracker.R

open class MenuDisplay {
    open fun mDisplay(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.menu_analysis)?.isVisible= false
        menu.findItem(R.id.menu_dashboard)?.isVisible= false
        menu.findItem(R.id.menu_record)?.isVisible= false
        menu.findItem(R.id.menu_transaction_history)?.isVisible= false
        menu.findItem(R.id.sort_expense)?.isVisible= false
        menu.findItem(R.id.sort_income)?.isVisible= false
    }
}