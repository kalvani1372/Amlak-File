package com.dev.amr.amlakfile.ui.fragment.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.base.BaseFragment
import com.dev.amr.amlakfile.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment() {

    private lateinit var binding : FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.layLogo.visibility = View.GONE
        binding.toolbar.txtTitle.visibility = View.VISIBLE
        binding.toolbar.txtTitle.text = resources.getString(R.string.setting)

        showDialogInactive(requireActivity())

    }


}