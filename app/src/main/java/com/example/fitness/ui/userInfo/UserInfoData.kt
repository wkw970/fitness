package com.example.fitness.ui.userInfo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//用户信息表 序列化便可以通过Intent传递对象
@Parcelize
data class userInfo(
    var icon:String="",//icon地址
    var nickname:String ="", //昵称作为主键 不可空
    var height : Int=0,
    var weight : Int=0,
    var bmi : Double=1.0, //身体质量指数
    var tel : String="",
    var gender : Boolean=true
):Parcelable