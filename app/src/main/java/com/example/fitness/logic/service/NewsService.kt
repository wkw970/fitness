package com.example.fitness.logic.service

import com.example.fitness.ui.community.news
import com.example.fitness.ui.community.newsContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsService {

    //获取动态内容
    @GET("get_news")
    fun getNews():List<Call<news>>


    //获取动态评论内容
    @GET("get_news_content")
    fun getNewsContent():List<Call<newsContent>>


    //发布动态
    //返回是否发布成功
    @POST("post_news")
    fun postNews():Call<Boolean>


    //发布评论
    @POST("post_content")
    fun postContent():Call<Boolean>

}