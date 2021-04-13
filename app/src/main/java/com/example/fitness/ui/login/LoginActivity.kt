package com.example.fitness.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fitness.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val navController = findNavController(R.id.login_userInfo_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_loginFragment,
                R.id.navigation_codeFragment,
                R.id.navigation_registerFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}