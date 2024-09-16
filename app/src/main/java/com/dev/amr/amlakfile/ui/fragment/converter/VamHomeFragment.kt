package com.dev.amr.amlakfile.ui.fragment.converter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityVamHomeBinding
import com.dev.amr.amlakfile.ui.fragment.converter.adapter.Installment
import com.dev.amr.amlakfile.ui.fragment.converter.adapter.InstallmentAdapter
import com.dev.amr.amlakfile.ui.fragment.converter.model.DateConverter
import java.text.DecimalFormat
import java.util.Calendar

class VamHomeFragment : Fragment() {

    private lateinit var binding: ActivityVamHomeBinding
    private val decimalFormat = DecimalFormat("#,###")
    @Suppress("UNREACHABLE_CODE")
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityVamHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.installmentRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        binding.calculateButton.setOnClickListener {
            val loanAmount = binding.loanAmountInput.text.toString().toDoubleOrNull()
            val interestRate = binding.interestRateInput.text.toString().toDoubleOrNull()
            val loanTermMonths = binding.loanTermInput.text.toString().toIntOrNull()

            if (loanAmount != null && interestRate != null && loanTermMonths != null) {
                val monthlyPayment = calculateMortgage(loanAmount, interestRate, loanTermMonths)
                binding.resultText.text = "قسط ماهانه وام : ${decimalFormat.format(monthlyPayment)}تومان "

                val installmentList = generateInstallments(loanTermMonths, monthlyPayment)
                val adapter = InstallmentAdapter(installmentList)
                binding.installmentRecyclerView.adapter = adapter
            } else {
                binding.resultText.text = "لطفاً همه مقادیر را به درستی وارد کنید"
            }
        }
    }

    // تابع محاسبه قسط ماهانه
    private fun calculateMortgage(P: Double, annualInterestRate: Double, months: Int): Double {
        return if (annualInterestRate == 0.0) {
            P / months
        } else {
            val r = (annualInterestRate / 100) / 12
            (P * r * Math.pow(1 + r, months.toDouble())) / (Math.pow(1 + r, months.toDouble()) - 1)
        }
    }

//     تابع تولید لیست اقساط با نام ماه شمسی و تاریخ پرداخت شمسی
    private fun generateInstallments(loanTermMonths: Int, monthlyPayment: Double): List<Installment> {
        val installmentList = mutableListOf<Installment>()
        val calendar = Calendar.getInstance()
        val dateConverter = DateConverter()

        // نام ماه‌های شمسی
        val monthNames = arrayOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور",
            "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند")

        for (i in 1..loanTermMonths) {
            // محاسبه تاریخ پرداخت
            val paymentDateMiladi = calendar.time
            val paymentDateShamsi = dateConverter.gregorianToJalali(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            val paymentDateFormatted = "${paymentDateShamsi[0]}/${paymentDateShamsi[1]}/${paymentDateShamsi[2]}"

            // انتخاب نام ماه شمسی و سال شمسی
            val monthIndex = calendar.get(Calendar.MONTH)
            val yearShamsi = paymentDateShamsi[0]
            val monthShamsi = monthNames[monthIndex]

            installmentList.add(Installment("$monthShamsi $yearShamsi", paymentDateFormatted, monthlyPayment))

            // حرکت به ماه بعدی
            calendar.add(Calendar.MONTH, 1)
        }
        return installmentList
    }

}