package com.dev.amr.amlakfile.ui.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.adapter.SliderAdapter
import com.dev.amr.amlakfile.data.hawk.Hawks
import com.dev.amr.amlakfile.data.model.model.Slid
import com.dev.amr.amlakfile.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.toolbar.btnMenu.setOnClickListener(this)
        binding.layRegisterKharidForosh.setOnClickListener(this)
        binding.layRegisterRahnEjare.setOnClickListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        getData()
    }

    @SuppressLint("WrongConstant")
    private fun bindViews() {
        val listStory: MutableList<Slid> = mutableListOf<Slid>()
        listStory.add(
            Slid(getString(R.string.txt_estelam_vekalatname), R.drawable.img_estelam_vekalatname))
        listStory.add(
            Slid(getString(R.string.txt_akharin_moamelat_mataghe), R.drawable.img_akharin_moamelat))
        listStory.add(
            Slid(getString(R.string.txt_estelam_chek_sayadi), R.drawable.img_estelam_chek))
        listStory.add(
            Slid(getString(R.string.txt_taeidiye_postal_code), R.drawable.img_taeidiye_postal_code))

        binding.viewPager.adapter = SliderAdapter(requireActivity(), listStory)
        binding.indicator.visibility = View.VISIBLE
        binding.indicator.setViewPager2(binding.viewPager)

        val handler = Handler(Looper.getMainLooper())
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(headerRunnable)
                handler.postDelayed(headerRunnable, 5000)
            }
        })

    }

    private val headerRunnable = Runnable {
        if (binding.viewPager.currentItem == 3) {
            binding.viewPager.currentItem = 0
        } else
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        binding.toolbar.txtTitle.text = getString(R.string.app_name)
        val name = Hawks.getString(Hawks.KEY_NAME)
        val family = Hawks.getString(Hawks.KEY_FAMILY)
        val amlakName = Hawks.getString(Hawks.KEY_NAME_AMLAK)
        val versionApp = Hawks.getString(Hawks.KEY_VERSION_APP)
        val fullName = "$name $family"

        binding.txtFullName.text = " نام کاربری : $fullName"
        binding.txtAmlakName.text = " نام املاک : $amlakName"
        binding.txtVersionApp.text = " نگارش : $versionApp"
    }

    @SuppressLint("WrongConstant")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.lay_register_kharid_forosh -> {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_homeFragment_to_registerBuyAndSellFragment)
            }

            R.id.lay_register_rahn_ejare -> {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_homeFragment_to_registerMortgageAndRentFragment)
            }

            R.id.btn_menu -> {
                if (!binding.drawer.isDrawerOpen(Gravity.END))
                    binding.drawer.openDrawer(Gravity.END)
                else binding.drawer.closeDrawer(Gravity.END)
            }

        }
    }
}