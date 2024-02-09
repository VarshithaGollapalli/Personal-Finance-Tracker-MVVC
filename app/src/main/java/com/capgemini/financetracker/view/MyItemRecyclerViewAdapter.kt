package com.capgemini.financetracker.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.capgemini.financetracker.R
import com.capgemini.financetracker.databinding.FragmentTransactionHistoryBinding
import com.capgemini.financetracker.model.TransactionData

import com.capgemini.financetracker.view.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentTransactionHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.typeTextView.text = item.type
        holder.category.text = item.category
        holder.description.text = item.description
        holder.amount.text = item.amount.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentTransactionHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val typeTextView: TextView = binding.typeT
        val category: TextView = binding.catT
        val description: TextView = binding.decT
        val amount: TextView = binding.amtT
//        val dateandtime: TextView = binding.dateV

    }

}