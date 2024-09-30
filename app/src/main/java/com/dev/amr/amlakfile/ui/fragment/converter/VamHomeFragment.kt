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
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityVamHomeBinding
import com.dev.amr.amlakfile.ui.fragment.converter.adapter.Installment
import com.dev.amr.amlakfile.ui.fragment.converter.adapter.InstallmentAdapter
import com.dev.amr.amlakfile.ui.fragment.converter.model.DateConverter
import com.github.yamin8000.ppn.PersianDigits
import java.text.DecimalFormat
import java.util.Calendar

class VamHomeFragment : Fragment() {

    private lateinit var binding: ActivityVamHomeBinding
    private val decimalFormat = DecimalFormat("#,###")

    private lateinit var installmentList : List<Installment>

    @Suppress("UNREACHABLE_CODE")
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityVamHomeBinding.inflate(layoutInflater)
        binding.toolbar.txtTitle.text = getString(R.string.txt_mohasebat_vam_maskan)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            binding.calculateButton.setOnClickListener {

                if (binding.loanAmountInput.text.toString() == ""){
                    binding.layWarning1.visibility = View.VISIBLE
                }else if (binding.interestRateInput.text.toString() == ""){
                    binding.layWarning2.visibility = View.VISIBLE
                }else if (binding.loanTermInput.text.toString() == ""){
                    binding.layWarning3.visibility = View.VISIBLE
                }else {
                    binding.layWarning1.visibility = View.GONE
                    binding.layWarning2.visibility = View.GONE
                    binding.layWarning3.visibility = View.GONE

                    binding.layShowDetailVam.visibility = View.VISIBLE

                    var loanAmount = binding.loanAmountInput.text.toString().toDoubleOrNull()
                    var interestRate = binding.interestRateInput.text.toString().toDoubleOrNull()
                    var loanTermMonths = binding.loanTermInput.text.toString().toIntOrNull()

                    if (loanAmount != null && interestRate != null && loanTermMonths != null) {
                        val monthlyPayment = calculateMortgage(loanAmount, interestRate, loanTermMonths)
                        binding.txtAghsadResult.text = decimalFormat.format(monthlyPayment)

                        installmentList = generateInstallments(loanTermMonths, monthlyPayment)

                }

            }}

            binding.loanAmountInput.doAfterTextChanged {
                if (binding.loanAmountInput.length() == 0) {
                    binding.txtPriceVam.visibility = View.GONE
                } else if (binding.loanAmountInput.length() != 0) {
                    binding.txtPriceVam.visibility = View.VISIBLE
                    val number = it.toString()
                    binding.txtPriceVam.text =
                        PersianDigits.spellToPersian(number.replace(",", "")) + " تومان"
                }
            }

            binding.btnShowListAghsad.setOnClickListener {

                binding.toolbar.txtTitle.text = getString(R.string.txt_list_vam_maskan)
                binding.cardViewItems.visibility = View.GONE
                binding.cardViewListAghsad.visibility = View.VISIBLE

                binding.installmentRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

                val adapter = InstallmentAdapter(installmentList)
                binding.installmentRecyclerView.adapter = adapter
            }

                binding.toolbar.btnBack.setOnClickListener {
                    findNavController().navigate(R.id.action_vamHomeFragment_to_homeFragment)
                }

        binding.loanAmountInput.addTextChangedListener {
            if (it.toString() != ""){
                binding.layWarning1.visibility = View.GONE
                binding.txtPriceVam.visibility = View.VISIBLE
            }else {
                binding.txtPriceVam.visibility = View.GONE
            }
        }

    }

    private fun calculateMortgage(P: Double, annualInterestRate: Double, months: Int): Double {
        return if (annualInterestRate == 0.0) {
            P / months
        } else {
            val r = (annualInterestRate / 100) / 12
            (P * r * Math.pow(1 + r, months.toDouble())) / (Math.pow(1 + r, months.toDouble()) - 1)
        }
    }

    private fun generateInstallments(loanTermMonths: Int, monthlyPayment: Double): List<Installment> {
        val installmentList = mutableListOf<Installment>()
        val calendar = Calendar.getInstance()
        val dateConverter = DateConverter()

        val monthNames = arrayOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور",
            "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند")

        for (i in 1..loanTermMonths) {

            val paymentDateMiladi = calendar.time
            val paymentDateShamsi = dateConverter.gregorianToJalali(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            val paymentDateFormatted = "${paymentDateShamsi[0]}/${paymentDateShamsi[1]}/${paymentDateShamsi[2]}"

            val monthIndex = calendar.get(Calendar.MONTH)
            val yearShamsi = paymentDateShamsi[0]
            val monthShamsi = monthNames[monthIndex]

            installmentList.add(Installment("$monthShamsi $yearShamsi", paymentDateFormatted, monthlyPayment))

            calendar.add(Calendar.MONTH, 1)
        }
        return installmentList
    }

}