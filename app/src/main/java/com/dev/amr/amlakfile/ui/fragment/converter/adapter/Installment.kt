package com.dev.amr.amlakfile.ui.fragment.converter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.R
import java.text.DecimalFormat

data class Installment(val installmentNumber: String, val paymentDate: String, val amount: Double)


    class InstallmentAdapter(private val installmentList: List<Installment>) :
        RecyclerView.Adapter<InstallmentAdapter.InstallmentViewHolder>() {

        private val decimalFormat = DecimalFormat("#,###")

        class InstallmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val installmentNumberText: TextView = itemView.findViewById(R.id.installmentNumberText)
            val paymentDateText: TextView = itemView.findViewById(R.id.paymentDateText)
            val amountText: TextView = itemView.findViewById(R.id.amountText)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.installment_item, parent, false)
            return InstallmentViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: InstallmentViewHolder, position: Int) {
            val installment = installmentList[position]
            holder.installmentNumberText.text = "قسط ${installment.installmentNumber}"
            holder.paymentDateText.text = installment.paymentDate
            holder.amountText.text = "${decimalFormat.format(installment.amount)} تومان "
        }

        override fun getItemCount(): Int {
            return installmentList.size
        }
    }
