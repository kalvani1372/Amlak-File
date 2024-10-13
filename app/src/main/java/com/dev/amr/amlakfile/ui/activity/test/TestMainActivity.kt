package com.dev.amr.amlakfile.ui.activity.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.model.custom_views.IEditText
import com.dev.amr.amlakfile.data.model.model.JDF
import com.dev.amr.amlakfile.databinding.ActivityTestMainBinding
import java.util.Date

class TestMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityTestMainBinding
    private var counter : Int = 0
    private var counterStepsOne : Int = 1
    private var counterStepsTwo : Int = 1
    private lateinit var newMonth: String
    private lateinit var newDay: String
    private var month = 0
    private var day = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestMainBinding.inflate(layoutInflater)
        binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_kharid)
        startSteps()
        textWatchers()
        binding.rBFormOne.setOnClickListener(this)
        binding.rBFormTow.setOnClickListener(this)

    }

    // todo >> Step One
    private fun startSteps() {
        // Todo Step One
        setContentView(binding.root)
        getCurrentDate(binding.layFormOne.edtDate,binding.layFormOne.edtTime)
        binding.btnNext123.setOnClickListener(this)
        binding.btnPrevious.setOnClickListener(this)

        // Todo Step Two
    }

    private fun textWatchers(){
        // Todo Text Watcher Step One
        binding.layFormOne.edtUserAdded.addTextChangedListener {
            if (binding.layFormOne.edtUserAdded.text.toString() != ""){
                binding.layFormOne.layWarning1.visibility = View.GONE
            }
        }
        binding.layFormOne.edtDate.addTextChangedListener {
            if (binding.layFormOne.edtDate.text.toString() != ""){
                binding.layFormOne.layWarning2.visibility = View.GONE
            }
        }
        binding.layFormOne.edtTime.addTextChangedListener {
            if (binding.layFormOne.edtTime.text.toString() != ""){
                binding.layFormOne.layWarning3.visibility = View.GONE
            }
        }

        // Todo Text Watcher Step Two
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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_next123 ->{
                when(counterStepsOne){
                    1 ->{
                        counterStepsOne = 1
                        if (binding.layFormOne.edtUserAdded.text.toString() == "" &&
                            binding.layFormOne.edtDate.text.toString() == "" &&
                            binding.layFormOne.edtTime.text.toString() == ""){

                            binding.layFormOne.layWarning1.visibility = View.VISIBLE
                            binding.layFormOne.layWarning2.visibility = View.VISIBLE
                            binding.layFormOne.layWarning3.visibility = View.VISIBLE

                        }else if (binding.layFormOne.edtDate.text.toString() == "" &&
                            binding.layFormOne.edtTime.text.toString() == ""){

                            binding.layFormOne.layWarning2.visibility = View.VISIBLE
                            binding.layFormOne.layWarning3.visibility = View.VISIBLE

                        }else if (binding.layFormOne.edtUserAdded.text.toString() == "" &&
                            binding.layFormOne.edtDate.text.toString() == ""){

                            binding.layFormOne.layWarning1.visibility = View.VISIBLE
                            binding.layFormOne.layWarning2.visibility = View.VISIBLE

                        }else if (binding.layFormOne.edtUserAdded.text.toString() == "" &&
                            binding.layFormOne.edtTime.text.toString() == ""){

                            binding.layFormOne.layWarning1.visibility = View.VISIBLE
                            binding.layFormOne.layWarning3.visibility = View.VISIBLE

                        }else if (binding.layFormOne.edtUserAdded.text.toString() == ""){
                            binding.layFormOne.layWarning1.visibility = View.VISIBLE

                        }else if (binding.layFormOne.edtDate.text.toString() == ""){
                            binding.layFormOne.layWarning2.visibility = View.VISIBLE

                        }else if (binding.layFormOne.edtTime.text.toString() == ""){
                            binding.layFormOne.layWarning3.visibility = View.VISIBLE

                        }else{
                            binding.layScroll.visibility = View.GONE
                            binding.layScroll2.visibility = View.VISIBLE
                            binding.circularProgressBar.progress = 40

                            binding.txtTop.text = "2"
                            binding.txtPageTitleTop.text = resources.getString(R.string.txt_information_owner)
                            binding.txtPageTitleBottom.text = resources.getString(R.string.txt_price_the_property)

                            binding.img.background = resources.getDrawable(R.drawable.user2)
                            binding.txtPageTitle.text = resources.getString(R.string.txt_information_owner)

                            binding.btnPrevious.visibility = View.VISIBLE
                        }
                    }
                    2 ->{}
                    3 ->{}
                    4 ->{}
                    5 ->{}
                }

            }
            R.id.btn_previous ->{
                when(counterStepsOne){
                    1 ->{
                        counterStepsOne = 1
                        binding.layScroll.visibility = View.VISIBLE
                        binding.layScroll2.visibility = View.GONE
                        binding.circularProgressBar.progress = 20

                        binding.txtTop.text = "1"
                        binding.txtPageTitleTop.text = resources.getString(R.string.txt_info_paye_registering)
                        binding.txtPageTitleBottom.text = resources.getString(R.string.txt_information_owner)

                        binding.img.background = resources.getDrawable(R.drawable.document_text)
                        binding.txtPageTitle.text = resources.getString(R.string.txt_info_paye_registering)

                        binding.btnPrevious.visibility = View.GONE
                    }
                    2 ->{}
                    3 ->{}
                    4 ->{}
                    5 ->{}
                }


            }
            R.id.r_b_form_one ->{
                counter = 1
                binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_kharid)
                binding.rBFormOne.background = resources.getDrawable(R.drawable.border7)
                binding.rBFormOne.setTextColor(resources.getColor(R.color.color_btn_login))

                binding.rBFormTow.background = resources.getDrawable(R.drawable.border6)
                binding.rBFormTow.setTextColor(resources.getColor(R.color.txt_color_description_login))
            }
            R.id.r_b_form_tow ->{
                counter = 2
                binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_all_jozeyat)
                binding.rBFormTow.background = resources.getDrawable(R.drawable.border7)
                binding.rBFormTow.setTextColor(resources.getColor(R.color.color_btn_login))

                binding.rBFormOne.background = resources.getDrawable(R.drawable.border6)
                binding.rBFormOne.setTextColor(resources.getColor(R.color.txt_color_description_login))
            }
        }
    }
}