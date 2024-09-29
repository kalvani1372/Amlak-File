package com.dev.amr.amlakfile.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityNumberRandomBinding
import kotlin.random.Random

class NumberRandomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNumberRandomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}