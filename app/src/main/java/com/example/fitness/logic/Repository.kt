package com.example.fitness.logic


import androidx.lifecycle.liveData
import com.example.fitness.logic.network.UserInfoNetwork
import com.example.fitness.ui.userInfo.userInfo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

object Repository {

    //使用livedata 异步更新数据
    fun getUserInfo(user:String,password:String) = liveData(Dispatchers.IO){
        val result = try {
            val user = UserInfoNetwork.getUserInfo(user,password)
            Result.success(user)
        }catch (e:Exception){

        }
        emit(result)
    }
}