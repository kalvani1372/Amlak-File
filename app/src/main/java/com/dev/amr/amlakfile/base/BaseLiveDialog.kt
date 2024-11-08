package com.dev.amr.amlakfile.base

import androidx.lifecycle.MutableLiveData

internal object BaseLiveDialog {

    var liveDataTypeUser = MutableLiveData<Boolean>()

    var liveDataBackToHomePage = MutableLiveData<Boolean>()

    var liveDataEmptyItems = MutableLiveData<Boolean>()

    var liveDataDoYouSure = MutableLiveData<Boolean>()

}