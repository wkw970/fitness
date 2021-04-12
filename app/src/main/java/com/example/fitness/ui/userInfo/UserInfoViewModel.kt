package com.example.fitness.ui.userInfo

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class UserInfoViewModel :ViewModel() {

    var user = MutableLiveData<userInfo>()

    //使得livedata改变
    fun setUser(user:userInfo){
        this.user.value=user
    }

    var iconUri =MutableLiveData<Uri>()

    fun setIconUri(uri: Uri)
    {
        this.iconUri.value=uri
    }
}