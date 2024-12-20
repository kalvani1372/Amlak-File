package com.dev.amr.amlakfile.ui.fragment.converter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ConvertDepositToRentAndItsReverseFragmentBinding
import com.dev.amr.amlakfile.utils.NumberTextWatcher
import com.github.yamin8000.ppn.PersianDigits
import java.text.DecimalFormat

@Suppress("UNREACHABLE_CODE")
class ConvertDepositToRentAndItsReverseFragment : Fragment() {

    private lateinit var binding: ConvertDepositToRentAndItsReverseFragmentBinding
    private var counter: Int = 1

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ConvertDepositToRentAndItsReverseFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack2.setOnClickListener {
            findNavController().navigate(R.id.action_convertDepositToRentAndItsReverseFragment_to_mainFragment)
        }

        // todo تبدیل رهن به اجاره
        binding.btnRahnEjare.setOnClickListener {
            counter = 1
            binding.edtMablaghRahn.setText("")
            binding.txtTitle.text = resources.getString(R.string.txt_convert_rahn_to_ejare)
            binding.btnRahnEjare.background = resources.getDrawable(R.drawable.border7)
            binding.btnRahnEjare.setTextColor(resources.getColor(R.color.color_btn_login))

            binding.btnEjareRahn.background = resources.getDrawable(R.drawable.border6)
            binding.btnEjareRahn.setTextColor(resources.getColor(R.color.txt_color_description_login))

            binding.inputTxtMablaghRahn.hint =
                resources.getString(R.string.txt_hint_mablagh_rahn_toman)
            binding.edtMablaghRahn.hint = resources.getString(R.string.txt_hint_mablagh_rahn)

            binding.txt.text = resources.getString(R.string.txt_ejare_mahiyane)
            binding.layShowDetailVam.visibility = View.GONE

        }

        binding.btnEjareRahn.setOnClickListener {
            counter = 2
            binding.edtMablaghRahn.setText("")
            binding.txtTitle.text = resources.getString(R.string.txt_convert_ejare_to_rahn)
            binding.btnEjareRahn.background = resources.getDrawable(R.drawable.border7)
            binding.btnEjareRahn.setTextColor(resources.getColor(R.color.color_btn_login))

            binding.btnRahnEjare.background = resources.getDrawable(R.drawable.border6)
            binding.btnRahnEjare.setTextColor(resources.getColor(R.color.txt_color_description_login))

            binding.inputTxtMablaghRahn.hint =
                resources.getString(R.string.txt_hint_mablagh_ejare_toman)
            binding.edtMablaghRahn.hint = resources.getString(R.string.txt_hint_mablagh_ejare)

            binding.txt.text = resources.getString(R.string.txt_mablagh_rahn_kamel)
            binding.layShowDetailVam.visibility = View.GONE

        }

        binding.edtMablaghRahn.addTextChangedListener(NumberTextWatcher(binding.edtMablaghRahn))
        binding.edtMablaghRahn.addTextChangedListener {
            if (it.toString() != "") {
                binding.layWarning.visibility = View.GONE
                binding.txtShowPrice.visibility = View.VISIBLE
            } else {
                binding.txtShowPrice.visibility = View.GONE
            }
        }
        binding.edtMablaghRahn.doAfterTextChanged {
            if (binding.edtMablaghRahn.length() == 0) {
                binding.txtShowPrice.visibility = View.GONE
            } else if (binding.edtMablaghRahn.length() != 0) {
                binding.txtShowPrice.visibility = View.VISIBLE
                val number = it.toString()
                binding.txtShowPrice.text =
                    PersianDigits.spellToPersian(number.replace(",", "")) + " تومان"
            }
        }


        binding.convertToRentButton.setOnClickListener {
            when (counter) {
                1 -> {
                    binding.layShowDetailVam.visibility = View.VISIBLE
                    if (binding.edtMablaghRahn.text.toString() == "") {
                        binding.layWarning.visibility = View.VISIBLE
                    } else {
                        val deposit = (binding.edtMablaghRahn.text.toString()).replace(",", "")
                            .toDoubleOrNull()
                        if (deposit != null) {
                            val rent = convertDepositToRent(deposit)
                            val decimalFormat = DecimalFormat("#,###")
                            val formattedDeposit = decimalFormat.format(rent)
                            binding.txtResult.text = formattedDeposit
                        } else {
                            binding.txtResult.text = "لطفاً مبلغ معتبر وارد کنید"
                        }
                    }
                }

                2 -> {
                    binding.layShowDetailVam.visibility = View.VISIBLE
                    if (binding.edtMablaghRahn.text.toString() == "") {
                        binding.layWarning.visibility = View.VISIBLE
                    } else {
                        val rent = (binding.edtMablaghRahn.text.toString()).replace(",", "")
                            .toDoubleOrNull()
                        if (rent != null) {
                            val deposit = convertRentToDeposit(rent)
                            val decimalFormat = DecimalFormat("#,###")
                            val formattedDeposit = decimalFormat.format(deposit)
                            binding.txtResult.text = formattedDeposit
                        } else {
                            binding.txtResult.text = "لطفاً مبلغ معتبر وارد کنید"
                        }
                    }
                }
            }
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