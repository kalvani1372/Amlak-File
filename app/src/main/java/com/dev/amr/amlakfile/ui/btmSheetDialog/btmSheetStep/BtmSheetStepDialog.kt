package com.dev.amr.amlakfile.ui.btmSheetDialog.btmSheetStep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.adapter.StepsAdapter
import com.dev.amr.amlakfile.data.model.model.Step
import com.dev.amr.amlakfile.databinding.BtmSheetStepDialogRowBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BtmSheetStepDialog : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var binding: BtmSheetStepDialogRowBinding

        private lateinit var viewModel: BtmSheetStepViewModel
    private lateinit var adapter: StepsAdapter

    private val _steps = MutableLiveData<List<Step>>()
    private val steps: LiveData<List<Step>> = _steps

    init {
        // Initialize steps
        _steps.value = listOf(
            Step("تایید اطلاعات پایه آگهی", false),
            Step("اطلاعات مالک", false),
            Step("قیمت", false),
            Step("آدرس و متراژ", false),
            Step("مشخصات کلیدی", false)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = BtmSheetStepDialogRowBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(BtmSheetStepViewModel::class.java)
        adapter = StepsAdapter(viewModel.steps.value ?: emptyList())
        binding.stepsRecyclerView.adapter = adapter
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onStepCompleted(5)

    }

    private fun onStepCompleted(index: Int) {
        viewModel.completeStep(index)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_cancel -> {
                dismiss()
            }
        }
    }
}