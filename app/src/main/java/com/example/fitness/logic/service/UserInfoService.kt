package com.example.fitness.logic.service

import com.example.fitness.ui.userInfo.userInfo
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

//用来获取用户的信息数据
interface UserInfoService {

    //查询输入的用户名密码是否正确
    //返回的是用户信息
    @GET("get_userInfo")
    fun getUserInfo(@Query("user")user:String,@Query("password")password:String): Call<userInfo>


    //用户注册
    @POST("post_userInfo")
    fun postUserInfo():Call<Boolean>

    //用户数据更新
    @Multipart
    @POST("update_userInfo")
    fun updateUserInfo(@Body user:userInfo,@Part("file") file: MultipartBody.Part):Call<Boolean>
}