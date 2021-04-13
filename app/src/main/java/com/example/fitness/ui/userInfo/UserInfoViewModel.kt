package com.example.fitness.ui.userInfo

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class UserInfoViewModel :ViewModel() {
    var isChange = MutableLiveData<Boolean>()
    var user = MutableLiveData<userInfo>()
    var iconUri =MutableLiveData<Uri>()

    init {
        isChange.value = false
    }

    //使得livedata改变
    fun setUser(user:userInfo){
        this.user.value=user
    }



    fun setIconUri(uri: Uri)
    {
        this.iconUri.value=uri
    }

    var nickname =MutableLiveData<String>()

    fun setNickname(nickname:String){
        this.nickname.value=nickname
    }




    fun setIsChange(change : Boolean){
        this.isChange.value=change
    }
}