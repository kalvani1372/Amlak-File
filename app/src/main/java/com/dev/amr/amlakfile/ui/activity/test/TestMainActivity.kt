package com.dev.amr.amlakfile.ui.activity.test

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentManager
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.model.custom_views.IEditText
import com.dev.amr.amlakfile.data.model.model.JDF
import com.dev.amr.amlakfile.databinding.ActivityTestMainBinding
import com.dev.amr.amlakfile.utils.NumberTextWatcher
import com.github.yamin8000.ppn.PersianDigits
import java.util.Date

class TestMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTestMainBinding
    private var counter: Int = 0
    private var counterStepsOne: Int = 1
    private var counterStepsTwo: Int = 1
    private lateinit var newMonth: String
    private lateinit var newDay: String
    private var month = 0
    private var day = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestMainBinding.inflate(layoutInflater)
        binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_kharid)
        getCurrentDate(binding.layFormOne.edtDate, binding.layFormOne.edtTime)
        textWatchers()
        binding.rBFormOne.setOnClickListener(this)
        binding.rBFormTow.setOnClickListener(this)
        binding.btnNext123.setOnClickListener(this)
        binding.btnPrevious.setOnClickListener(this)
        binding.layFormFive.radioBtn1.setOnClickListener(this)
        binding.layFormFive.radioBtn2.setOnClickListener(this)

        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    private fun textWatchers() {
        // Todo Text Watcher Step One
        binding.layFormOne.edtUserRegistering.addTextChangedListener {
            if (binding.layFormOne.edtUserRegistering.text.toString() != "") {
                binding.layFormOne.layWarning1.visibility = View.GONE
            }
        }
        binding.layFormOne.edtDate.addTextChangedListener {
            if (binding.layFormOne.edtDate.text.toString() != "") {
                binding.layFormOne.layWarning2.visibility = View.GONE
            }
        }
        binding.layFormOne.edtTime.addTextChangedListener {
            if (binding.layFormOne.edtTime.text.toString() != "") {
                binding.layFormOne.layWarning3.visibility = View.GONE
            }
        }

        // Todo Text Watcher Step Two
        binding.layFormTwo.edtNameOwner.addTextChangedListener {
            if (binding.layFormTwo.edtNameOwner.text.toString() != "") {
                binding.layFormTwo.layWarning1.visibility = View.GONE
            }
        }
        binding.layFormTwo.edtFamilyOwner.addTextChangedListener {
            if (binding.layFormTwo.edtFamilyOwner.text.toString() != "") {
                binding.layFormTwo.layWarning2.visibility = View.GONE
            }
        }
        binding.layFormTwo.edtMobilePhoneNumber.addTextChangedListener {
            if (binding.layFormTwo.edtMobilePhoneNumber.text.toString() != "") {
                binding.layFormTwo.edtMobilePhoneNumber.letterSpacing = 1F
                binding.layFormTwo.layWarning3.visibility = View.GONE
            }
        }

        // Todo Text Watcher Step Three
        binding.layFormThree.edtPriceMelk.addTextChangedListener {
            if (binding.layFormThree.edtPriceMelk.text.toString() != "") {
                binding.layFormThree.layWarning1.visibility = View.GONE
                NumberTextWatcher(binding.layFormThree.edtPriceMelk)
            }
        }
        binding.layFormThree.edtPriceMelk.addTextChangedListener(
            NumberTextWatcher(binding.layFormThree.edtPriceMelk)
        )
        binding.layFormThree.edtPriceMelk.doAfterTextChanged {
            if (binding.layFormThree.edtPriceMelk.length() == 0) {
                binding.layFormThree.txtPrice.visibility = View.GONE
            } else if (binding.layFormThree.edtPriceMelk.length() != 0) {
                binding.layFormThree.txtPrice.visibility = View.VISIBLE
                val number = it.toString()
                binding.layFormThree.txtPrice.text =
                    PersianDigits.spellToPersian(number.replace(",", "")) + " تومان"
            }
        }

        // Todo Text Watcher Step Three
        binding.layFormFour.edtAddressFile.addTextChangedListener {
            if (binding.layFormFour.edtAddressFile.text.toString() != "") {
                binding.layFormFour.layWarning1.visibility = View.GONE
            }
        }
        binding.layFormFour.edtMetrazhMoraba.addTextChangedListener {
            if (binding.layFormFour.edtMetrazhMoraba.text.toString() != "") {
                binding.layFormFour.layWarning2.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun getCurrentDate(dateFormOne: IEditText, timeFormOne: IEditText) {
        val jdf = JDF()
        val date = Date()
        month = jdf.iranianMonth
        day = jdf.iranianDay
        if (month < 10) {
            newMonth = "0" + jdf.iranianMonth
        } else {
            newMonth = java.lang.String.valueOf(jdf.iranianMonth)
        }
        if (day < 10) {
            newDay = "0$day"
        } else {
            newDay = day.toString()
        }
        val hourString = if (date.hours < 10) "0" + date.hours else "" + date.hours
        val minuteString = if (date.minutes < 10) "0" + date.minutes else "" + date.minutes
        val time = "$hourString : $minuteString"

        dateFormOne.setText("${jdf.iranianYear} / $newMonth / $newDay")
        timeFormOne.setText(time)

//        dateFormTow.setText("${jdf.iranianYear} / $newMonth / $newDay")
//        timeFormTow.setText(time)
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.r_b_form_one -> {
                counter = 1
                binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_kharid)
                binding.rBFormOne.background = resources.getDrawable(R.drawable.border7)
                binding.rBFormOne.setTextColor(resources.getColor(R.color.color_btn_login))

                binding.rBFormTow.background = resources.getDrawable(R.drawable.border6)
                binding.rBFormTow.setTextColor(resources.getColor(R.color.txt_color_description_login))
            }
            R.id.r_b_form_tow -> {
                counter = 2
                binding.toolbar.txtTitle.text =
                    resources.getString(R.string.txt_register_all_jozeyat)
                binding.rBFormTow.background = resources.getDrawable(R.drawable.border7)
                binding.rBFormTow.setTextColor(resources.getColor(R.color.color_btn_login))

                binding.rBFormOne.background = resources.getDrawable(R.drawable.border6)
                binding.rBFormOne.setTextColor(resources.getColor(R.color.txt_color_description_login))
            }
            R.id.btn_next123 -> {
                when (counterStepsOne) {
                    1 -> {
                        counterStepsOne = 1
                        if (binding.layFormOne.edtUserRegistering.text.toString() == "" &&
                            binding.layFormOne.edtDate.text.toString() == "" &&
                            binding.layFormOne.edtTime.text.toString() == ""
                        ) {

                            binding.layFormOne.layWarning1.visibility = View.VISIBLE
                            binding.layFormOne.layWarning2.visibility = View.VISIBLE
                            binding.layFormOne.layWarning3.visibility = View.VISIBLE

                        } else if (binding.layFormOne.edtDate.text.toString() == "" &&
                            binding.layFormOne.edtTime.text.toString() == ""
                        ) {

                            binding.layFormOne.layWarning2.visibility = View.VISIBLE
                            binding.layFormOne.layWarning3.visibility = View.VISIBLE

                        } else if (binding.layFormOne.edtUserRegistering.text.toString() == "" &&
                            binding.layFormOne.edtDate.text.toString() == ""
                        ) {

                            binding.layFormOne.layWarning1.visibility = View.VISIBLE
                            binding.layFormOne.layWarning2.visibility = View.VISIBLE

                        } else if (binding.layFormOne.edtUserRegistering.text.toString() == "" &&
                            binding.layFormOne.edtTime.text.toString() == ""
                        ) {

                            binding.layFormOne.layWarning1.visibility = View.VISIBLE
                            binding.layFormOne.layWarning3.visibility = View.VISIBLE

                        } else if (binding.layFormOne.edtUserRegistering.text.toString() == "") {
                            binding.layFormOne.layWarning1.visibility = View.VISIBLE

                        } else if (binding.layFormOne.edtDate.text.toString() == "") {
                            binding.layFormOne.layWarning2.visibility = View.VISIBLE

                        } else if (binding.layFormOne.edtTime.text.toString() == "") {
                            binding.layFormOne.layWarning3.visibility = View.VISIBLE

                        } else {
                            counterStepsOne = 2
                            binding.layScroll.visibility = View.GONE
                            binding.layScroll2.visibility = View.VISIBLE
                            binding.circularProgressBar.progress = 40

                            binding.txtTop.text = "2"
                            binding.txtPageTitleTop.text =
                                resources.getString(R.string.txt_information_owner)
                            binding.txtPageTitleBottom.text = " بعدی : ${resources.getString(R.string.txt_price_the_property)}"

                            binding.img.background = resources.getDrawable(R.drawable.user2)
                            binding.txtPageTitle.text =
                                resources.getString(R.string.txt_information_owner)

                            binding.btnPrevious.visibility = View.VISIBLE
                        }
                    }

                    2 -> {
                        counterStepsOne = 2
                        if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                            binding.layFormTwo.edtFamilyOwner.text.toString() == "" &&
                            binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                        ) {

                            binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                            binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                            binding.layFormTwo.layWarning3.visibility = View.VISIBLE

                        } else if (binding.layFormTwo.edtFamilyOwner.text.toString() == "" &&
                            binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                        ) {

                            binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                            binding.layFormTwo.layWarning3.visibility = View.VISIBLE

                        } else if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                            binding.layFormTwo.edtFamilyOwner.text.toString() == ""
                        ) {

                            binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                            binding.layFormTwo.layWarning2.visibility = View.VISIBLE

                        } else if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                            binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                        ) {

                            binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                            binding.layFormTwo.layWarning3.visibility = View.VISIBLE

                        } else if (binding.layFormTwo.edtNameOwner.text.toString() == "") {
                            binding.layFormTwo.layWarning1.visibility = View.VISIBLE

                        } else if (binding.layFormTwo.edtFamilyOwner.text.toString() == "") {
                            binding.layFormTwo.layWarning2.visibility = View.VISIBLE

                        } else if (binding.layFormTwo.edtMobilePhoneNumber.text.toString() == "") {
                            binding.layFormTwo.layWarning3.visibility = View.VISIBLE

                        } else {
                            counterStepsOne = 3
                            binding.layScroll.visibility = View.GONE
                            binding.layScroll2.visibility = View.GONE
                            binding.layScroll3.visibility = View.VISIBLE
                            binding.circularProgressBar.progress = 60

                            binding.txtTop.text = "3"
                            binding.txtPageTitleTop.text =
                                resources.getString(R.string.txt_price_the_property)
                            binding.txtPageTitleBottom.text = " بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


                            binding.img.background = resources.getDrawable(R.drawable.money_bag)
                            binding.txtPageTitle.text =
                                resources.getString(R.string.txt_price_the_property)

                            binding.btnPrevious.visibility = View.VISIBLE
                        }
                    }

                    3 -> {
                        counterStepsOne = 3
                         if (binding.layFormThree.edtPriceMelk.text.toString() == "") {
                            binding.layFormThree.layWarning1.visibility = View.VISIBLE

                        } else {
                            counterStepsOne = 4
                            binding.layScroll.visibility = View.GONE
                            binding.layScroll2.visibility = View.GONE
                            binding.layScroll3.visibility = View.GONE
                            binding.layScroll4.visibility = View.VISIBLE
                            binding.circularProgressBar.progress = 80

                            binding.txtTop.text = "4"
                            binding.txtPageTitleTop.text =
                                resources.getString(R.string.txt_address_and_metrazh)
                            binding.txtPageTitleBottom.text =" بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                            binding.img.background = resources.getDrawable(R.drawable.map_point)
                            binding.txtPageTitle.text =
                                resources.getString(R.string.txt_address_and_metrazh)

                        }
                    }
                    4 -> {
                        counterStepsOne = 4
                        if (binding.layFormFour.edtAddressFile.text.toString() == "" &&
                            binding.layFormFour.edtMetrazhMoraba.text.toString() == "") {
                            binding.layFormFour.layWarning1.visibility = View.VISIBLE
                            binding.layFormFour.layWarning2.visibility = View.VISIBLE

                        } else if (binding.layFormFour.edtAddressFile.text.toString() == "") {
                            binding.layFormFour.layWarning1.visibility = View.VISIBLE

                        } else if (binding.layFormFour.edtMetrazhMoraba.text.toString() == "") {
                            binding.layFormFour.layWarning2.visibility = View.VISIBLE

                        } else {
                            counterStepsOne = 5
                            binding.layScroll.visibility = View.GONE
                            binding.layScroll2.visibility = View.GONE
                            binding.layScroll3.visibility = View.GONE
                            binding.layScroll4.visibility = View.GONE
                            binding.layScroll5.visibility = View.VISIBLE
                            binding.circularProgressBar.progress = 100

                            binding.btnNext123.text = resources.getString(R.string.txt_register2)

                            binding.txtTop.text = "5"
                            binding.txtPageTitleTop.text =
                                resources.getString(R.string.txt_moshakhasat_kilidi)
                            binding.layDes2.visibility = View.GONE


                            binding.img.background = resources.getDrawable(R.drawable.key_square)
                            binding.txtPageTitle.text =
                                resources.getString(R.string.txt_moshakhasat_kilidi)
                        }
                    }
                    5 -> {}
                }

            }
            R.id.btn_previous -> {
                when (counterStepsOne) {
//                    1 ->{
//                        counterStepsOne = 1
//                        binding.layScroll.visibility = View.VISIBLE
//                        binding.layScroll2.visibility = View.GONE
//                        binding.circularProgressBar.progress = 20
//
//                        binding.txtTop.text = "1"
//                        binding.txtPageTitleTop.text = resources.getString(R.string.txt_info_paye_registering)
//                        binding.txtPageTitleBottom.text = resources.getString(R.string.txt_information_owner)
//
//                        binding.img.background = resources.getDrawable(R.drawable.document_text)
//                        binding.txtPageTitle.text = resources.getString(R.string.txt_info_paye_registering)
//
//                        binding.btnPrevious.visibility = View.GONE
//                    }
                    2 -> {
                        counterStepsOne = 1
                        binding.layScroll.visibility = View.VISIBLE
                        binding.layScroll2.visibility = View.GONE
                        binding.circularProgressBar.progress = 20

                        binding.txtTop.text = "1"
                        binding.txtPageTitleTop.text =
                            resources.getString(R.string.txt_info_paye_registering)
                        binding.txtPageTitleBottom.text = " بعدی : ${resources.getString(R.string.txt_information_owner)}"


                        binding.img.background = resources.getDrawable(R.drawable.document_text)
                        binding.txtPageTitle.text =
                            resources.getString(R.string.txt_info_paye_registering)

                        binding.btnPrevious.visibility = View.GONE

                    }

                    3 -> {
                        counterStepsOne = 2
                        binding.layScroll.visibility = View.GONE
                        binding.layScroll2.visibility = View.VISIBLE
                        binding.layScroll3.visibility = View.GONE
                        binding.circularProgressBar.progress = 40

                        binding.txtTop.text = "2"
                        binding.txtPageTitleTop.text =
                            resources.getString(R.string.txt_information_owner)
                        binding.txtPageTitleBottom.text = " بعدی : ${resources.getString(R.string.txt_price_the_property)}"


                        binding.img.background = resources.getDrawable(R.drawable.user2)
                        binding.txtPageTitle.text =
                            resources.getString(R.string.txt_information_owner)
                    }

                    4 -> {
                        counterStepsOne = 3
                        binding.layScroll.visibility = View.GONE
                        binding.layScroll2.visibility = View.GONE
                        binding.layScroll3.visibility = View.VISIBLE
                        binding.layScroll4.visibility = View.GONE
                        binding.circularProgressBar.progress = 60

                        binding.txtTop.text = "3"
                        binding.txtPageTitleTop.text =
                            resources.getString(R.string.txt_price_the_property)
                        binding.txtPageTitleBottom.text = " بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


                        binding.img.background = resources.getDrawable(R.drawable.money_bag)
                        binding.txtPageTitle.text =
                            resources.getString(R.string.txt_price_the_property)
                    }
                    5 -> {
                        counterStepsOne = 4
                        binding.layScroll.visibility = View.GONE
                        binding.layScroll2.visibility = View.GONE
                        binding.layScroll3.visibility = View.GONE
                        binding.layScroll4.visibility = View.VISIBLE
                        binding.layScroll5.visibility = View.GONE
                        binding.circularProgressBar.progress = 80

                        binding.txtTop.text = "4"
                        binding.txtPageTitleTop.text =
                            resources.getString(R.string.txt_address_and_metrazh)
                        binding.layDes2.visibility = View.VISIBLE
                        binding.txtPageTitleBottom.text = " بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                        binding.img.background = resources.getDrawable(R.drawable.map_point)
                        binding.txtPageTitle.text =
                            resources.getString(R.string.txt_price_the_property)
                    }
                }


            }
            R.id.radio_btn1 ->{
                binding.layFormFive.radioBtn1.isChecked = true
                binding.layFormFive.radioBtn2.isChecked = false
                binding.layFormFive.layRadioBtn1.background = resources.getDrawable(R.drawable.border9)
                binding.layFormFive.layRadioBtn2.background = resources.getDrawable(R.drawable.border4)
            }
            R.id.radio_btn2 ->{
                binding.layFormFive.radioBtn1.isChecked = false
                binding.layFormFive.radioBtn2.isChecked = true
                binding.layFormFive.layRadioBtn1.background = resources.getDrawable(R.drawable.border4)
                binding.layFormFive.layRadioBtn2.background = resources.getDrawable(R.drawable.border9)
            }
        }
    }
}