package com.dev.amr.amlakfile.ui.btmSheetDialog.btmSheetStep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.amr.amlakfile.data.model.model.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
//import okhttp3.ResponseBody
import javax.inject.Inject
class BtmSheetStepViewModel : ViewModel(){
    private val _steps = MutableLiveData<List<Step>>()
    val steps: LiveData<List<Step>> = _steps

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

    fun completeStep(index: Int) {
        _steps.value = _steps.value?.mapIndexed { i, step ->
            if (i == index) step.copy(isCompleted = true) else step
        }
    }
}
