package com.example.fitness.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.example.fitness.MainActivity
import com.example.fitness.R
import com.example.fitness.logic.network.UserInfoNetwork
import com.example.fitness.ui.userInfo.userInfo
import com.example.fitness.utli.makeToast
import com.mob.MobSDK
import kotlinx.android.synthetic.main.fragment_login_code.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CodeFragment : Fragment() {
    val userInfoViewModel by lazy { activity?.let { ViewModelProvider(it).get(UserInfoViewModel::class.java) } }

    //smssdk中的事件处理类
    lateinit var handler: EventHandler

    private lateinit var tel: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login_code, container, false)
        tel = userInfoViewModel?.tel?.value as String
        MobSDK.init(context, "314c796008a54", "125e3c6be1a6c852d1d9766f2cb8d635")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SMSSDK事件注册
        handler = object : EventHandler() {
            override fun afterEvent(p0: Int, p1: Int, p2: Any?) {
                super.afterEvent(p0, p1, p2)
                if (p1 == SMSSDK.RESULT_COMPLETE) {
                    if (p0 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        judgeIsExistUser()
                    }
                } else {
                    (p2 as Throwable).printStackTrace()
                }
            }
        }
        SMSSDK.registerEventHandler(handler)
        //登录按键注册事件
        fragment_login_code_enter.setOnClickListener {
            val code = fragment_login_code_code.text.toString()
            SMSSDK.submitVerificationCode("86", tel, code)
        }
    }

    fun judgeIsExistUser() {
        var userInfo: userInfo
        //开启协程 回调函数结束后会返回到这
        GlobalScope.launch {
            userInfo = UserInfoNetwork.queryByTel(tel)
            //意思是没有该用户
            //跳转到注册界面
            if (userInfo.nickname == "nnn") {
                userInfoViewModel?.setTel(tel)
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_codeFragment_to_registerFragment)
                }
            } else {
                context?.let { "验证成功!".makeToast(it) }
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("userinfo", userInfo)
                startActivity(intent)
            }

        }

    }
}