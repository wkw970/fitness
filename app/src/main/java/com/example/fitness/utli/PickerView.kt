package com.example.fitness.utli

import android.content.Context
import android.graphics.Color
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener

/**
 * 选择框相关操作类
 */
object PickerView {

    //显示选择框 去选择男女性别 使用泛类的方法
    //可能返回空
    fun <T>showGenderSelect(context: Context,list:List<T>):T?{
        var selected : T? =null
        val genderSelect = OptionsPickerBuilder(context, OnOptionsSelectListener{
                options1, options2, options3, v ->
            selected=list[options1]

        }).setDividerColor(Color.BLACK)
            .setTextColorCenter(Color.BLACK)
            .setContentTextSize(20)
            .build<T>()

        genderSelect.setPicker(list)
        genderSelect.show()

        return selected
    }
}