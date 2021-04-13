package com.example.fitness.logic.network

import com.example.fitness.logic.service.UserInfoService
import com.example.fitness.ui.userInfo.userInfo
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//单例类 用来处理用户信息请求
object UserInfoNetwork {

    //生成一个动态代理 可以实现里面的所有方法
    private val userInfoService = ServiceCreator.create<UserInfoService>()


    suspend fun getUserInfo(user: String, password: String) =
        userInfoService.getUserInfo(user, password).await()

    //根据手机号判断用户是否存在
    suspend fun queryByTel(tel: String) = userInfoService.queryByTel(tel).await()


    //上传头像,User信息
    suspend fun updateUserInfo(file: File,tel:String){
        val requestFile:RequestBody = RequestBody.create(MediaType.parse("form-data"),file)

        var fileName = ""
        try {
            fileName = URLEncoder.encode(file.name,"UTF-8")
        }catch (e:UnsupportedEncodingException){
            e.printStackTrace()
        }

        val body:MultipartBody.Part =MultipartBody.Part.createFormData("file",fileName,requestFile)

        userInfoService.updateUserIcon(body,tel).await()

    }


    //协程 挂起函数 目的是进行网络请求访问的时候，会将执行函数挂起，当请求完成后将响应返回进行处理
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                        if (body!=null) it.resume(body)

                }

            })
        }
    }




}