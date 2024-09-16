package com.dev.amr.amlakfile.ui.fragment.login

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.hawk.Hawks
import com.dev.amr.amlakfile.databinding.FragmentLoginBinding

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var fullName: String
    private lateinit var amlakName: String
    private lateinit var phoneNumber: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shart()

        binding.edtName.addTextChangedListener { shart() }
        binding.edtNameAmlak.addTextChangedListener { shart() }
        binding.edtPhoneNumber.addTextChangedListener {
            if (it?.length != 11) {
                binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_gray)
                binding.btnContinue.isEnabled = false
            } else {
                shart()
            }
        }

        binding.btnContinue.setOnClickListener {

            fullName = binding.edtName.text.toString()
            Hawks.save(Hawks.KEY_NAME, fullName)

            amlakName = binding.edtNameAmlak.text.toString()
            Hawks.save(Hawks.KEY_NAME_AMLAK, amlakName)

            phoneNumber = binding.edtPhoneNumber.text.toString()
            Hawks.save(Hawks.KEY_PHONE_NUMBER, phoneNumber)

            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

        }


    }

    private fun shart() {
        if (binding.edtName.text.toString() != "" &&
            binding.edtNameAmlak.text.toString() != "" &&
            binding.edtPhoneNumber.text.toString() != ""
        ) {

            binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_blue)
            binding.btnContinue.isEnabled = true

        } else {
            binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_gray)
            binding.btnContinue.isEnabled = false
        }
    }

}