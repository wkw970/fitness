package com.example.fitness

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitness.ui.userInfo.UserInfoViewModel
import com.example.fitness.ui.userInfo.userInfo

class MainActivity : AppCompatActivity() {

    val userInfoViewModel by lazy { ViewModelProvider(this).get(UserInfoViewModel::class.java) }

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
        val user = intent.getParcelableExtra<userInfo>("userInfo")as userInfo
        userInfoViewModel.setUser(user)


    }
}