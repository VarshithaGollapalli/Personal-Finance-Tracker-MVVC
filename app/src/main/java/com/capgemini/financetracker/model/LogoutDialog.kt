package com.capgemini.personalfinanacetracker.model

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.capgemini.financetracker.R

class LogoutDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ctx = requireContext()
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle(getString(R.string.confirmation_title))
        builder.setMessage(getString(R.string.logout_msg))
        builder.setPositiveButton(getString(R.string.yes_button)) { _, _ ->
            activity?.finish()
        }
        builder.setNegativeButton(getString(R.string.no_button)) { dlg, _ ->
            dlg.cancel()
        }
        return builder.create()
    }
}