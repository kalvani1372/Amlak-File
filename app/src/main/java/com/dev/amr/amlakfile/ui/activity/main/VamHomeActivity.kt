package com.dev.amr.amlakfile.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityVamHomeBinding
import com.dev.amr.amlakfile.ui.activity.main.adapter.PaymentsAdapter
import com.dev.amr.amlakfile.ui.activity.main.model.PaymentDetail
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.pow

class VamHomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVamHomeBinding
    private lateinit var paymentsAdapter: PaymentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVamHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

// تنظیم RecyclerView
        binding.paymentsRecyclerView.layoutManager = LinearLayoutManager(this)
        paymentsAdapter = PaymentsAdapter()
        binding.paymentsRecyclerView.adapter = paymentsAdapter

        // دکمه محاسبه اقساط ماهانه
        binding.calculateButton.setOnClickListener {
            val loanAmount = binding.loanAmountEditText.text.toString().toDoubleOrNull()
            val annualInterestRate = binding.interestRateEditText.text.toString().toDoubleOrNull()
            val loanTerm = binding.loanTermEditText.text.toString().toIntOrNull()

            if (loanAmount != null && annualInterestRate != null && loanTerm != null) {
                // محاسبه لیست اقساط ماهانه با تاریخ
                val payments = calculateMonthlyPayments(loanAmount, annualInterestRate, loanTerm)
                paymentsAdapter.updatePayments(payments)
            } else {
                paymentsAdapter.updatePayments(emptyList())
            }
        }
    }

    // تابع محاسبه لیست اقساط ماهانه
    private fun calculateMonthlyPayments(loanAmount: Double, annualInterestRate: Double, loanTerm: Int): List<PaymentDetail> {
        val monthlyInterestRate = annualInterestRate / 100 / 12
        val numberOfPayments = loanTerm
        val payments = mutableListOf<PaymentDetail>()
        val monthlyPayment = if (monthlyInterestRate == 0.0) {
            loanAmount / numberOfPayments
        } else {
            val numerator = monthlyInterestRate * (1 + monthlyInterestRate).pow(numberOfPayments)
            val denominator = (1 + monthlyInterestRate).pow(numberOfPayments) - 1
            loanAmount * (numerator / denominator)
        }
        val startDate = Calendar.getInstance()
        for (i in 0 until numberOfPayments) {
            val paymentDate = Calendar.getInstance()
            paymentDate.time = startDate.time
            paymentDate.add(Calendar.MONTH, i)
            payments.add(PaymentDetail(toPersianDate(paymentDate.time), monthlyPayment))
        }
        return payments
    }

    // تابع تبدیل تاریخ میلادی به تاریخ شمسی
    private fun toPersianDate(date: Date): String {
        val gregorianCalendar = Calendar.getInstance()
        gregorianCalendar.time = date
        val year = gregorianCalendar.get(Calendar.YEAR)
        val month = gregorianCalendar.get(Calendar.MONTH) + 1
        val day = gregorianCalendar.get(Calendar.DAY_OF_MONTH)

        // تبدیل سال، ماه و روز میلادی به شمسی
        val persianYear = year - 621
        val persianMonth = month // تبدیل ماه به شمسی
        val persianDay = day // تبدیل روز به شمسی

        // فرمت تاریخ شمسی به صورت "yyyy/MM/dd"
        return String.format("%04d/%02d/%02d", persianYear, persianMonth, persianDay)
    }
}