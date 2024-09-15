package com.dev.amr.amlakfile.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.amr.amlakfile.databinding.ActivityTestBinding
import java.text.DecimalFormat

class ConvertDepositToRentAndItsReverseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // todo تبدیل رهن به اجاره
        binding.convertToRentButton.setOnClickListener {
            val deposit = binding.inputValue.text.toString().toDoubleOrNull()
            if (deposit != null) {
                val rent = convertDepositToRent(deposit)
                val decimalFormat = DecimalFormat("#,###")
                val formattedDeposit = decimalFormat.format(rent)
                binding.resultTextView.text = "اجاره معادل: ${formattedDeposit} تومان"
            } else {
                binding.resultTextView.text = "لطفاً مبلغ معتبر وارد کنید"
            }
        }

        // todo تبدیل اجاره به رهن
        binding.convertToDepositButton.setOnClickListener {
            val rent = binding.inputValue.text.toString().toDoubleOrNull()
            if (rent != null) {
                val deposit = convertRentToDeposit(rent)
                val decimalFormat = DecimalFormat("#,###")
                val formattedDeposit = decimalFormat.format(deposit)
                binding.resultTextView.text = "رهن معادل: ${formattedDeposit} تومان"
            } else {
                binding.resultTextView.text = "لطفاً مبلغ معتبر وارد کنید"
            }
        }

        // دکمه بازنشانی
        binding.resetButton.setOnClickListener {
            binding.inputValue.text.clear()
            binding.resultTextView.text = "نتیجه"
        }

    }

    //  todo فرمول تبدیل رهن به اجاره
    private fun convertDepositToRent(deposit: Double): Double {
        return (deposit / 1000000) * 30000
    }

    // todo فرمول تبدیل اجاره به رهن
    private fun convertRentToDeposit(rent: Double): Double {
        return (rent / 30000) * 1000000
    }


    }


