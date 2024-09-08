package com.dev.amr.amlakfile.ui.fragment.login

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private lateinit var name: String
    private lateinit var family: String
    private lateinit var username: String
    private lateinit var province: String
    private lateinit var city: String
    private lateinit var amlakName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gson = Gson()
        var reader: JsonReader? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            reader = JsonReader(
                InputStreamReader(
                    requireActivity().assets.open("province.txt"),
                    StandardCharsets.UTF_8)
            )
        }

        binding.btnNextProgram.setOnClickListener {

            name = binding.edtName.text.toString()
            Hawks.save(Hawks.KEY_NAME, name)
            family = binding.edtFamily.text.toString()
            Hawks.save(Hawks.KEY_FAMILY, family)
            username = binding.edtUsername.text.toString()
            Hawks.save(Hawks.KEY_USERNAME, username)



            province = binding.edtProvince.text.toString()
            Hawks.save(Hawks.KEY_PROVINCE, province)
            city = binding.edtCity.text.toString()
            Hawks.save(Hawks.KEY_CITY, city)
            amlakName = binding.edtNameAmlak.text.toString()
            Hawks.save(Hawks.KEY_NAME_AMLAK, amlakName)

            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

        }


    }

}