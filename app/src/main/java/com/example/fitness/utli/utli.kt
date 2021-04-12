package com.example.fitness.utli

import android.content.Context
import android.widget.Toast
import java.time.Duration

//默认情况下是短时间显示
fun String.makeToast(context: Context,duration: Int=Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}