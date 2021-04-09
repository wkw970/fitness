package com.example.fitness.ui.userInfo
//用户信息表
data class userInfo(
    val id: String,
    val nickname:String,
    val height : Int,
    val weight : Int,
    val bmi : Float, //身体质量指数
    val tel : String,
    val gender : Boolean
)