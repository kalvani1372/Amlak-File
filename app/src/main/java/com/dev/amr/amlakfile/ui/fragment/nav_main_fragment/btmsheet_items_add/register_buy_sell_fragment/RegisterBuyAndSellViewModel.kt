package com.dev.amr.amlakfile.ui.fragment.nav_main_fragment.btmsheet_items_add.register_buy_sell_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class RegisterBuyAndSellViewModel{}
//@Inject constructor(private val repository: RegisterBuyAndSellRepository) : ViewModel() {
//
//    private val _getRegisterBuyAndSell = MutableLiveData<String>()
//    var getRegisterBuyAndSellLiveData : LiveData<String> = _getRegisterBuyAndSell
//
//    fun getAddress(lat : String, lng : String){
//        viewModelScope.launch {
//            val response = repository.getConvertLocInAddress(lat, lng)
//
//            response.body()!!.let {
//                _getRegisterBuyAndSell.postValue(it.toString())
//            }
//        }
//    }
//}
