package com.dev.amr.amlakfile.ui.btmSheetDialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.adapter.DottedLineAdapter
import com.dev.amr.amlakfile.data.model.custom_views.ITextView
import com.dev.amr.amlakfile.databinding.BottomSheetStepsNewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StepsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding : BottomSheetStepsNewBinding
    private lateinit var stepViews: List<ITextView>
    private lateinit var stepViewsPoint: List<View>
    val textViews = listOf(
        binding.step1 ,binding.step2, binding.step3 ,binding.step4,binding.step5
    )
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BottomSheetStepsNewBinding.inflate(layoutInflater)

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = DottedLineAdapter(10)

        binding.recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView2.adapter = DottedLineAdapter(10)

        binding.recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView3.adapter = DottedLineAdapter(10)

        binding.recyclerView4.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView4.adapter = DottedLineAdapter(10)



        val steps = listOf(
            Pair("تایید اطلاعات پایه آگهی", false),
            Pair("اطلاعات مالک", false),
            Pair("قیمت", false),
            Pair("آدرس و متراژ", false),
            Pair("مشخصات کلیدی", false)
        )

        // تغییر رنگ متن‌ها بر اساس وضعیت
        steps.forEachIndexed { index, step ->
            val (title, isCompleted) = step
            textViews[index].text = title
            textViews[index].setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    if (isCompleted) R.color.color_btn_login else R.color.gray
                )
            )
        }

//        stepViews = listOf(
//            binding.step1,
//            binding.step2,
//            binding.step3,
//            binding.step4,
//            binding.step5
//        )
//
//        simulateSteps(5)

        return binding.root
    }

    private fun simulateSteps(counter : Int) {
        for (i in stepViews.indices) {
            stepViews[i].isSelected = i < counter // رنگ سبز برای مراحل تکمیل‌شده
        }
    }
}