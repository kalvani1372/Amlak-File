package com.dev.amr.amlakfile.ui.fragment.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.databinding.FragmentReportsBinding

class ReportsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentReportsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReportsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtFroshMaskoni.setOnClickListener(this)
        binding.txtEjareMaskoni.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txt_frosh_maskoni -> {
//                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_FMFragment)
            }

            R.id.txt_ejare_maskoni -> {
//                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_EMFragment)
            }

            R.id.txt_ejare_edari -> {
//                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_EMFragment)
            }

            R.id.txt_frosh_edari -> {
//                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_EMFragment)
            }

            R.id.txt_project_sakhtosaz -> {
//                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_EMFragment)
            }
        }
    }
}