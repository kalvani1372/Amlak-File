package com.dev.amr.amlakfile.base

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.model.custom_views.IEditText
import com.dev.amr.amlakfile.data.model.model.JDF
import com.dev.amr.amlakfile.databinding.DialogDoYouContinueLayoutBinding
import com.dev.amr.amlakfile.databinding.DialogInactiveLayoutBinding
import java.io.ByteArrayOutputStream
import java.util.Date

abstract class BaseFragment : Fragment() {

    private lateinit var dialog : AlertDialog
    private lateinit var newMonth: String
    private lateinit var newDay: String
    private var month = 0
    private var day = 0

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }

    fun showDialogDoYouContinue(context: Context){
        val binding = DialogDoYouContinueLayoutBinding.inflate(layoutInflater)
        dialog = AlertDialog.Builder(context, R.style.CustomAlertDialog).create()
        dialog.setView(binding.root)

        binding.btnNext.setOnClickListener {
            dialog.dismiss()
            BaseLiveDialog.liveDataEmptyItems.value = true
        }

        binding.btnCancel.setOnClickListener{
            BaseLiveDialog.liveDataBackToHomePage.value = true
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.show()
    }
    fun showDialogInactive(context: Context){
        val binding = DialogInactiveLayoutBinding.inflate(layoutInflater)
        dialog = AlertDialog.Builder(context, R.style.CustomAlertDialog).create()
        dialog.setView(binding.root)

        binding.btnNext.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.show()
    }

    fun bitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    @SuppressLint("SetTextI18n")
    fun getCurrentDate(dateFormOne: IEditText, timeFormOne: IEditText,
        dateFormTow: IEditText, timeFormTow: IEditText) {
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

        dateFormTow.setText("${jdf.iranianYear} / $newMonth / $newDay")
        timeFormTow.setText(time)
    }

//    fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int,dateFormOne: IEditText,dateFormTow: IEditText) {
//        var stringOfDate = year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth
//        val month: Int = monthOfYear + 1
//        val newmonth: String
//        val newday: String
//        newmonth = if (month < 10) {
//            "0$month"
//        } else {
//            month.toString()
//        }
//        newday = if (dayOfMonth < 10) {
//            "0$dayOfMonth"
//        } else {
//            dayOfMonth.toString()
//        }
//        stringOfDate = "$year/$newmonth/$newday"
//        dateFormOne.setText("")
//        dateFormOne.setText(stringOfDate)
//        dateFormTow.setText("")
//        dateFormTow.setText(stringOfDate)
//        onDateSet("",stringOfDate,stringOfDate)
//
//    }
//
//    fun onDateSet(dateFormOne: IEditText,dateFormTow: IEditText,stringOfDate : String){
//        dateFormOne.setText("")
//        dateFormOne.setText(stringOfDate)
//        dateFormTow.setText("")
//        dateFormTow.setText(stringOfDate)
//    }
//
//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//        val hourString = if (hourOfDay < 10) "0$hourOfDay" else "" + hourOfDay
//        val minuteString = if (minute < 10) "0$minute" else "" + minute
//        val time = "$hourString:$minuteString"
//        binding.layFormTow.edtTime.setText("")
//        binding.layFormTow.edtTime.setText(time)
//        binding.layFormOne.edtTime.setText("")
//        binding.layFormOne.edtTime.setText(time)
//    }
}