package com.capgemini.personalfinanacetracker.model

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class LogoutDialog(): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ctx = requireContext()
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle("Confirmation")
        builder.setMessage("Do you want to Logout?")
        builder.setPositiveButton("YES") { _, _ ->
            activity?.finish()
            //val intent = //to login page
        }
        builder.setNegativeButton("NO") { dlg, _ ->
            dlg.cancel()
        }
        return builder.create()
    }
}