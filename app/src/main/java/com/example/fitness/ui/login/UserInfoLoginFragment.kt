package com.example.fitness.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.MainActivity
import com.example.fitness.R
import com.example.fitness.logic.network.UserInfoNetwork
import com.example.fitness.ui.userInfo.userInfo
import com.example.fitness.utli.makeToast
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserInfoLoginFragment : Fragment() {

    lateinit var user:userInfo


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login,container,false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_login_enter.setOnClickListener {
            if(judgeInfo())
            {
                //通过网络访问得到的用户信息类
                val userName =fragment_login_user.text.toString()
                val password = fragment_login_password.text.toString()
                user = userInfo()
                GlobalScope.launch {
                    user=UserInfoNetwork.getUserInfo(userName,password)
                    //如果获得的user信息不为空就跳转
                    if(user.nickname != "nnn")
                    {
                        val intent =Intent(activity,MainActivity::class.java)
                        intent.putExtra("userInfo",user)
                        startActivity(intent)
                    }
                    else{

                    }

                }
            }

        }
    }

    //检查是否正确输入
    private fun judgeInfo():Boolean
    {
        var flag = false
        var user = fragment_login_user.text
        var password =fragment_login_password.text
        if (user.isEmpty()||password.isEmpty())
            context?.let { "输入的用户名或密码为空".makeToast(it) }
        else
            flag =true

        return flag
    }

}