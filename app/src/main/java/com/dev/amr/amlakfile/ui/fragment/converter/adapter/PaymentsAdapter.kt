package com.dev.amr.amlakfile.ui.fragment.converter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.databinding.ItemPaymentBinding
import com.dev.amr.amlakfile.ui.fragment.converter.model.PaymentDetail
import java.text.DecimalFormat

class PaymentsAdapter : RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder>() {

    private var payments: List<PaymentDetail> = emptyList()

    private val decimalFormat = DecimalFormat("#,###")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = payments[position]
        holder.bind(payment)
    }

    override fun getItemCount(): Int = payments.size

    fun updatePayments(newPayments: List<PaymentDetail>) {
        payments = newPayments
        notifyDataSetChanged()
    }

    inner class PaymentViewHolder(private val binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentDetail: PaymentDetail) {
            binding.paymentTextView.text = "قسط ماهانه: ${decimalFormat.format(paymentDetail.amount)} تومان"
            binding.dateTextView.text = "تاریخ پرداخت: ${paymentDetail.date}"
        }
    }
}
