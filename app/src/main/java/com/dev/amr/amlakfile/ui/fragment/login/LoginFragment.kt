package com.dev.amr.amlakfile.ui.fragment.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.hawk.Hawks
import com.dev.amr.amlakfile.databinding.FragmentLoginBinding
import kotlin.random.Random

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var fullName: String
    private lateinit var amlakName: String
    private lateinit var phoneNumber: String
    private var counter: Int = 0
    private var randomNumber: Int = 0
    private var secondsRemaining = 90
    private val handler = Handler()
    private var isTimerRunning = false
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ifPhoneNumber()

        binding.edtName.addTextChangedListener { ifRegister() }
        binding.edtNameAmlak.addTextChangedListener { ifRegister() }


        binding.edtPhoneNumber.addTextChangedListener {
            binding.edtPhoneNumber.letterSpacing = 0.7F
            if (it?.length != 11) {
                binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_gray)
                binding.btnContinue.isEnabled = false
            } else {
                ifPhoneNumber()
            }
        }

        binding.btnContinue.setOnClickListener {

            when (counter) {
                1 -> {
                    timer()
                    startTimer()
                    binding.layPhoneNumber.visibility = View.GONE
                    binding.layVerificationCode.visibility = View.VISIBLE

                    binding.btnContinue.background =
                        resources.getDrawable(R.drawable.btn_shape_gray)
                    binding.btnContinue.isEnabled = false

                    randomNumber = Random.nextInt(1000, 9999)
                    handler.postDelayed({
                        binding.edtVerificationCode1.setText(
                            randomNumber.toString().substring(0, 1)
                        )
                        binding.edtVerificationCode2.setText(
                            randomNumber.toString().substring(1, 2)
                        )
                        binding.edtVerificationCode3.setText(
                            randomNumber.toString().substring(2, 3)
                        )
                        binding.edtVerificationCode4.setText(randomNumber.toString().substring(3))

                        binding.btnContinue.background =
                            resources.getDrawable(R.drawable.btn_shape_purple)
                        binding.btnContinue.isEnabled = true

                        counter = 2

                    }, 3000)

                    binding.txtShowPhoneNumber.text = phoneNumber
                    Hawks.save(Hawks.KEY_PHONE_NUMBER, phoneNumber)

                    binding.btnContinue.text = resources.getString(R.string.txt_ok)
                }

                2 -> {
                    ifVerificationCode()
                }

                3 -> {
                    ifRegister()
                    findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
                }

                else -> {
                    randomNumber = 0
                    binding.layPhoneNumber.visibility = View.GONE
                    binding.layVerificationCode.visibility = View.VISIBLE
                    binding.txtShowPhoneNumber.text = ""
                    binding.edtVerificationCode1.setText("")
                    binding.edtVerificationCode2.setText("")
                    binding.edtVerificationCode3.setText("")
                    binding.edtVerificationCode4.setText("")
                    binding.edtPhoneNumber.letterSpacing = 0F
                    resetTimer()
                    binding.btnContinue.text = resources.getString(R.string.txt_login)
                }
            }

        }

        binding.txtEditPhoneNumber.setOnClickListener {
            randomNumber = 0
            binding.layPhoneNumber.visibility = View.VISIBLE
            binding.layVerificationCode.visibility = View.GONE
            binding.txtShowPhoneNumber.text = ""
            binding.edtPhoneNumber.setText("")
            binding.edtVerificationCode1.setText("")
            binding.edtVerificationCode2.setText("")
            binding.edtVerificationCode3.setText("")
            binding.edtVerificationCode4.setText("")
            binding.edtPhoneNumber.letterSpacing = 0F
            resetTimer()
            binding.btnContinue.text = resources.getString(R.string.txt_login)
        }


    }

    private fun timer() {
        runnable = object : Runnable {
            override fun run() {
                if (secondsRemaining > 0) {
                    // محاسبه دقیقه و ثانیه
                    val minutes = secondsRemaining / 60
                    val seconds = secondsRemaining % 60

                    // به‌روزرسانی نمایش تایمر
                    binding.txtTimer.text = String.format("%02d:%02d", minutes, seconds)

                    secondsRemaining--
                    // اجرای مجدد این Runnable بعد از 1 ثانیه
                    handler.postDelayed(this, 1000)
                } else {
                    // تایمر تمام شده
                    binding.txtTimer.text = ""
                    isTimerRunning = false
                }
            }
        }
    }

    private fun startTimer() {
        if (!isTimerRunning) {
            isTimerRunning = true
            handler.post(runnable)
        }
    }

    private fun resetTimer() {
        handler.removeCallbacks(runnable)
        isTimerRunning = false

        secondsRemaining = 90

        val minutes = secondsRemaining / 60
        val seconds = secondsRemaining % 60
        binding.txtTimer.text = String.format("%02d:%02d", minutes, seconds)

        startTimer()
    }

    private fun ifPhoneNumber() {
        if (binding.edtPhoneNumber.text.toString() != "") {

            binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_purple)
            binding.btnContinue.isEnabled = true
            phoneNumber = binding.edtPhoneNumber.text.toString()
            counter = 1

        } else {
            binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_gray)
            binding.btnContinue.isEnabled = false
            counter = 0
        }
    }

    private fun ifVerificationCode() {

        if (binding.edtVerificationCode1.text.toString() != "" &&
            binding.edtVerificationCode2.text.toString() != "" &&
            binding.edtVerificationCode3.text.toString() != "" &&
            binding.edtVerificationCode4.text.toString() != ""
        ) {
            val code1 = binding.edtVerificationCode1.text.toString()
            val code2 = binding.edtVerificationCode2.text.toString()
            val code3 = binding.edtVerificationCode3.text.toString()
            val code4 = binding.edtVerificationCode4.text.toString()
            val result = code1 + code2 + code3 + code4


            if (randomNumber.toString() == result) {
                binding.layPhoneNumber.visibility = View.GONE
                binding.layVerificationCode.visibility = View.GONE
                binding.layRegister.visibility = View.VISIBLE
                binding.btnContinue.text = resources.getString(R.string.txt_register)

                binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_gray)
                binding.btnContinue.isEnabled = false
                counter = 3
            } else {
                // todo نمایش خطا
            }

        }
    }

    private fun ifRegister() {
        if (binding.edtName.text.toString() != "" &&
            binding.edtNameAmlak.text.toString() != ""
        ) {

            binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_purple)
            binding.btnContinue.isEnabled = true

            fullName = binding.edtName.text.toString()
            Hawks.save(Hawks.KEY_NAME, fullName)

            amlakName = binding.edtNameAmlak.text.toString()
            Hawks.save(Hawks.KEY_NAME_AMLAK, amlakName)
        } else {
            binding.btnContinue.background = resources.getDrawable(R.drawable.btn_shape_gray)
            binding.btnContinue.isEnabled = false
        }
    }

}