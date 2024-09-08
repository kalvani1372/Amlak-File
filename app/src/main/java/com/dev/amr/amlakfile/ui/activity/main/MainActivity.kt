package com.dev.amr.amlakfile.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.amr.amlakfile.databinding.ActivityMainBinding
import com.orhanobut.hawk.Hawk

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Hawk.init(applicationContext).build()

    }
}