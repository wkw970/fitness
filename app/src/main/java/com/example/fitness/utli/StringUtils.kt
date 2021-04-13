package com.example.fitness.utli

import android.content.Context
import java.util.regex.Pattern

object StringUtils {
    //检查是否正确输入
    fun checkPhoneNumber(context:Context,tel:String):Boolean
    {
        var flag = false
        val regExp="^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$"
        val p = Pattern.compile(regExp)
        if (tel.isEmpty())
            context?.let { "请输入手机号!".makeToast(it) }
        else{
            val m=p.matcher(tel)    //利用正则表示判断当前手机号是否输入正确
            flag = m.matches()
        }
        if(!flag)
            "请输入正确的手机号".makeToast(context)

        return flag
    }

}