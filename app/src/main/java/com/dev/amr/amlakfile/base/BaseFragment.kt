package com.dev.amr.amlakfile.base

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.DialogDoYouContinueLayoutBinding
import com.dev.amr.amlakfile.databinding.DialogInactiveLayoutBinding
import java.io.ByteArrayOutputStream

abstract class BaseFragment : Fragment() {

    private lateinit var dialog : AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
}