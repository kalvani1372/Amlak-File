package com.dev.amr.amlakfile.ui.activity.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NavigationUI.setupWithNavController(binding.btmNavMenu,
            Navigation.findNavController(this,R.id.hostHomeFragment))
    }

}