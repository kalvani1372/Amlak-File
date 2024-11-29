package com.dev.amr.amlakfile.ui.btmSheetDialog

import android.annotation.SuppressLint
import android.content.Context
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
import com.dev.amr.amlakfile.data.hawk.Hawks
import com.dev.amr.amlakfile.databinding.BtmSheetStepsStepOneBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@Suppress("NAME_SHADOWING")
class StepsStepOneBtmSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BtmSheetStepsStepOneBinding

    private lateinit var textViews: List<TextView>

    private var pendingStepUpdate: Pair<Int, Boolean>? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = BtmSheetStepsStepOneBinding.inflate(layoutInflater)

        textViews = listOf(
            binding.step1, binding.step2, binding.step3, binding.step4, binding.step5
        )

        loadAndDisplaySteps(inflater.context)

        pendingStepUpdate?.let { (stepId, isCompleted) ->
            simulateSteps(stepId, isCompleted, textViews)
            pendingStepUpdate = null // ریست کردن متغیر
        }

        setupRecyclerView(binding.recyclerView)
        setupRecyclerView(binding.recyclerView2)
        setupRecyclerView(binding.recyclerView3)
        setupRecyclerView(binding.recyclerView4)


        return binding.root
    }

    fun deleteAllStepsStatus(): List<Unit> {
        return List(5) { index -> Hawks.delete("step_${index + 1}") }
    }

    private fun saveAllStepsStatus(isCompletedList: List<Boolean>) {
        isCompletedList.forEachIndexed { index, isCompleted ->
            Hawks.save("step_${index + 1}", isCompleted)
        }
    }

    private fun loadAllStepsStatus(): List<Boolean> {
        return List(5) { index -> Hawks.getBoolean("step_${index + 1}", false) }
    }

    private fun loadAndDisplaySteps(context: Context) {
        val steps = listOf(
            "تایید اطلاعات پایه آگهی",
            "اطلاعات مالک",
            "قیمت",
            "آدرس و متراژ",
            "مشخصات کلیدی"
        )

        val stepStatuses = loadAllStepsStatus()

        steps.forEachIndexed { index, title ->
            val isCompleted = stepStatuses[index]
            textViews[index].text = title
            textViews[index].setTextColor(
                ContextCompat.getColor(
                    context,
                    if (isCompleted) R.color.color_btn_login else R.color.gray
                )
            )
        }
    }

    fun updateSteps(stepId: Int, isCompleted: Boolean, context: Context) {

        if (!::textViews.isInitialized) {
            pendingStepUpdate = Pair(stepId, isCompleted)
            return
        }
        val stepStatuses = loadAllStepsStatus().toMutableList()
        stepStatuses[stepId - 1] = isCompleted

        saveAllStepsStatus(stepStatuses)

        loadAndDisplaySteps(context)
    }

    private fun simulateSteps(idStep: Int, checkStep: Boolean, textViews: List<TextView>) {

        // تعریف لیست مراحل به همراه وضعیت پیش‌فرض (false)
        val steps = mutableListOf(
            Pair("تایید اطلاعات پایه آگهی", false),
            Pair("اطلاعات مالک", false),
            Pair("قیمت", false),
            Pair("آدرس و متراژ", false),
            Pair("مشخصات کلیدی", false)
        )

        // تغییر وضعیت مرحله خاص بر اساس idStep
        if (idStep in 1..steps.size) {
            steps[idStep - 1] = steps[idStep - 1].copy(second = checkStep)
        }

        // تغییر رنگ متن‌ها بر اساس وضعیت
        steps.forEachIndexed { index, step ->
            val (title, isCompleted) = step
            textViews[index].text = title
            textViews[index].setTextColor(
                ContextCompat.getColor(
                    textViews[index].context,
                    if (isCompleted) R.color.color_btn_login else R.color.gray
                )
            )
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = DottedLineAdapter(10)
    }
}