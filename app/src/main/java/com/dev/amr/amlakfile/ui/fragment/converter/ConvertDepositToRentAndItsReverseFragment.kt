package com.dev.amr.amlakfile.ui.fragment.converter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityTestBinding
import java.text.DecimalFormat

@Suppress("UNREACHABLE_CODE")
class ConvertDepositToRentAndItsReverseFragment : Fragment() {

    private lateinit var binding : ActivityTestBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityTestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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