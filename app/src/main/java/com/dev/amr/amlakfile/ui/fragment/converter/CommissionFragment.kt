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
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityCommissionBinding
import com.dev.amr.amlakfile.ui.fragment.converter.model.CalculateCommissionAndTax
import com.dev.amr.amlakfile.utils.NumberTextWatcher
import com.github.yamin8000.ppn.PersianDigits
import java.text.DecimalFormat

class CommissionFragment : Fragment() {

    private lateinit var binding : ActivityCommissionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityCommissionBinding.inflate(layoutInflater)
        binding.toolbar.layLogo.visibility = View.GONE
        binding.toolbar.layBtnBack.visibility = View.VISIBLE
        binding.toolbar.txtTitle.text = resources.getString(R.string.txt_mohasebe_komisiyon)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtBaseAmountInput.addTextChangedListener (NumberTextWatcher(binding.edtBaseAmountInput))
        binding.edtBaseAmountInput.addTextChangedListener {
            if (it.toString() != "") {
                binding.layWarning1.visibility = View.GONE
                binding.txtPricePaye.visibility = View.VISIBLE
            } else {
                binding.txtPricePaye.visibility = View.GONE
            }
        }
        binding.edtBaseAmountInput.doAfterTextChanged {
            if (binding.edtBaseAmountInput.length() == 0) {
                binding.txtPricePaye.visibility = View.GONE
            } else if (binding.edtBaseAmountInput.length() != 0) {
                binding.txtPricePaye.visibility = View.VISIBLE
                val number = it.toString()
                binding.txtPricePaye.text =
                    PersianDigits.spellToPersian(number.replace(",", "")) + " تومان"
            }
        }

        binding.calculateButton.setOnClickListener{
            if (binding.edtBaseAmountInput.text.toString() == "" &&
                binding.edtCommissionRateInput.text.toString() == "" &&
                binding.edtVatRatInput.text.toString() == "") {
                binding.layWarning1.visibility = View.VISIBLE
                binding.layWarning2.visibility = View.VISIBLE
                binding.layWarning3.visibility = View.VISIBLE
            } else if (binding.edtBaseAmountInput.text.toString() == "") {
                binding.layWarning1.visibility = View.VISIBLE
            } else if (binding.edtCommissionRateInput.text.toString() == "") {
                binding.layWarning2.visibility = View.VISIBLE
            } else if (binding.edtVatRatInput.text.toString() == "") {
                binding.layWarning3.visibility = View.VISIBLE
            } else {
                binding.layWarning1.visibility = View.GONE
                binding.layWarning2.visibility = View.GONE
                binding.layWarning3.visibility = View.GONE

                binding.layShowDetailCommission.visibility = View.VISIBLE

                val baseAmount = (binding.edtBaseAmountInput.text.toString()).replace(",","").toDouble()
                val commissionRate = binding.edtCommissionRateInput.text.toString().toDouble()
                val vatRate = binding.edtVatRatInput.text.toString().toDouble()

                val result = calculateCommissionAndTax(
                    baseAmount, commissionRate, vatRate
                )

                binding.txtCommissionResult.text = result["commission"]!!.trimIndent()
                binding.txtMaliyatResult.text = result["vat"]!!.trimIndent()
                binding.txtMjmoeNahayiResult.text = result["totalAmount"]!!.trimIndent()

            }
        }

        binding.toolbar.btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_commissionFragment_to_mainFragment)
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
            "commission" to "${decimalFormat.format(commission)}",
            "vat" to "${decimalFormat.format(vat)}",
            "totalAmount" to "${decimalFormat.format(totalAmount)}"
        )
    }


}


