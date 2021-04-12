package com.example.fitness.ui.bodyBuilding

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class body(
    val name : String,//需要锻炼的地方
    val graph :String,//模块背景图片
    val content :String //锻炼方法
):Parcelable
