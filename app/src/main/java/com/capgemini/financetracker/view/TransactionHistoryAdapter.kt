package com.capgemini.financetracker.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.capgemini.financetracker.databinding.FragmentTransactionHistoryBinding

import com.capgemini.financetracker.view.placeholder.PlaceholderContent.PlaceholderItem
import com.capgemini.personalfinanacetracker.model.FinancialDataEntry

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class TransactionHistoryAdapter(
    private var values: List<FinancialDataEntry>
) : RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {

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
        holder.amount.text = "Rs. ${item.amount.toString()}"
        holder.date.text = item.date.toString()
    }


    override fun getItemCount(): Int = values.size
    fun updateData(sortedData: List<FinancialDataEntry>) {
        values = sortedData
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentTransactionHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val typeTextView: TextView = binding.typeT
        val category: TextView = binding.catT
        val description: TextView = binding.decT
        val amount: TextView = binding.amtT
        val date: TextView = binding.dateV

        val imageView: ImageView =binding.imageView

    }

}