package com.dev.amr.amlakfile.ui.fragment.converter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityCommissionBinding
import com.dev.amr.amlakfile.ui.fragment.converter.model.CalculateCommissionAndTax
import java.text.DecimalFormat

@Suppress("UNREACHABLE_CODE")
class CommissionFragment : Fragment() {

    private lateinit var binding : ActivityCommissionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityCommissionBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateButton.setOnClickListener {
            val baseAmount = binding.baseAmountInput.text.toString().toDouble()
            val commissionRate = binding.commissionRateInput.text.toString().toDouble()
            val vatRate = binding.vatRateInput.text.toString().toDouble()

            val result = calculateCommissionAndTax(
                baseAmount, commissionRate, vatRate
            )

            val resultString = """
                کمیسیون: ${result["commission"]}
                مالیات بر ارزش افزوده: ${result["vat"]}
                مجموع نهایی: ${result["totalAmount"]}
            """.trimIndent()

            binding.resultText.text = resultString
        }

    }
    fun calculateCommissionAndTax(
        baseAmount: Double,  // مبلغ پایه
        commissionRate: Double,  // درصد کمیسیون
        vatRate: Double  // درصد مالیات بر ارزش افزوده
    ): Map<String, String> {

        // برای فرمت کردن اعداد به صورت هزارگان
        val decimalFormat = DecimalFormat("#,###")

        // محاسبه کمیسیون
        val commission = baseAmount * (commissionRate / 100)

        // محاسبه مالیات بر ارزش افزوده
        val vat = commission * (vatRate / 100)

        // مجموع مبلغ کمیسیون و مالیات
        val totalAmount = commission + vat

        // برگرداندن نتایج به صورت فرمت تومان
        return mapOf(
            "commission" to "${decimalFormat.format(commission)} تومان",
            "vat" to "${decimalFormat.format(vat)} تومان",
            "totalAmount" to "${decimalFormat.format(totalAmount)} تومان"
        )
    }


}


