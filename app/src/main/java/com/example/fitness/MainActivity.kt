package com.example.fitness

import android.app.Application
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitness.logic.network.UserInfoNetwork
import com.example.fitness.ui.userInfo.UserInfoViewModel
import com.example.fitness.ui.userInfo.userInfo
import com.example.fitness.utli.FileUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity() {

    //更新操作放在活动销毁

    val userInfoViewModel by lazy { ViewModelProvider(this).get(UserInfoViewModel::class.java) }
    lateinit var userInfo: userInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(

                R.id.navigation_home, R.id.navigation_body,R.id.navigation_plus,R.id.navigation_community,R.id.navigation_userInfo))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //获取LoginActivity传过来用户信息
        //val user = intent.getParcelableExtra<userInfo>("userInfo")as userInfo
        userInfo = userInfo("","Bob",178,60,0.0,"13566897681","男")
        userInfoViewModel.setUser(userInfo)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onStop() {
        super.onStop()
        judgeIsSame()
    }

    //检查当前用户是否信息是否一致
    //昵称
    //头像

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun judgeIsSame(){
        //如果头像改变了

            if (userInfoViewModel.isChange.value as Boolean)
            {
                val file = File(FileUtils.getPath( applicationContext,userInfoViewModel.iconUri.value as Uri))
                GlobalScope.launch {
                    UserInfoNetwork.updateUserInfo(file,userInfo.tel)
                }
            }
    }
}