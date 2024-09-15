package com.dev.amr.amlakfile.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityCommissionBinding
import java.text.DecimalFormat

class CommissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // فرمت‌دهی اعداد بزرگ با کاما
        val decimalFormat = DecimalFormat("#,###")

        // درصد کمیسیون (به عنوان مثال ۱٪)
        val commissionRate = 0.01

        // دکمه محاسبه کمیسیون
        binding.calculateButton.setOnClickListener {
            val price = binding.priceEditText.text.toString().toDoubleOrNull()
            if (price != null) {
                // محاسبه کمیسیون
                val commission = price * commissionRate
                val formattedCommission = decimalFormat.format(commission)
                binding.resultTextView.text = "کمیسیون: $formattedCommission تومان"
            } else {
                binding.resultTextView.text = "لطفاً یک عدد معتبر وارد کنید"
            }
        }

    }
}