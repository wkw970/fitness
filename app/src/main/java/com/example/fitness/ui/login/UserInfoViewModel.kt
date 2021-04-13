package com.example.fitness.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//用户信息viewmodel
class UserInfoViewModel :ViewModel() {
    var tel = MutableLiveData<String>()

    fun setTel(tel:String){
        this.tel.value = tel
    }
}