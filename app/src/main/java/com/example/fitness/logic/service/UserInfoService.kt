package com.example.fitness.logic.service

import com.example.fitness.ui.userInfo.userInfo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

//用来获取用户的信息数据
interface UserInfoService {

    //查询输入的用户名密码是否正确
    //返回的是用户信息
    @GET("get_userInfo")
    fun getUserInfo(@Query("user")user:String,@Query("password")password:String): Call<userInfo>

    /**
     * 根据手机号查询是否存在该用户
     */
    @GET("get_queryByTel")
    fun queryByTel(@Query("tel")tel:String):Call<userInfo>


    //用户注册
    @POST("post_userInfo")
    fun postUserInfo():Call<Boolean>

    /**
     * 根据用户手机号更新用户icon
     */
    @Multipart
    @POST("post_update_icon")
    fun updateUserIcon(@Part file: MultipartBody.Part,@Query("tel")tel:String):Call<Boolean>

    /**
     * 传入用户实体类更新信息
     */
    @POST("post_update_userInfo")
    fun updateUserInfo(@Body user:userInfo):Call<Boolean>


}