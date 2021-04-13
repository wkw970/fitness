package com.example.fitness.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.example.fitness.MainActivity
import com.example.fitness.R
import com.example.fitness.logic.network.UserInfoNetwork
import com.example.fitness.ui.userInfo.userInfo
import com.example.fitness.utli.StringUtils
import com.example.fitness.utli.makeToast
import com.mob.MobSDK
import kotlinx.android.synthetic.main.fragment_login.*

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import kotlin.concurrent.thread

/**
 * 手机验证码登录界面
 * 根据手机号发送验证码->
 * 输入验证码正确->
 * 根据手机号查询是否有该用户->
 * 用户名不为特定值直接进入->否则输入性别、身高、体重、昵称进行数据存储
 */
class LoginFragment : Fragment() {

    lateinit var user: userInfo

    //smssdk中的事件处理类
    lateinit var handler: EventHandler

    lateinit var tel: String

    private val userInfoViewModel by lazy {
        activity?.let {
            ViewModelProvider(it).get(
                UserInfoViewModel::class.java
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        MobSDK.init(context, "314c796008a54", "125e3c6be1a6c852d1d9766f2cb8d635")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler = object : EventHandler() {
            override fun afterEvent(p0: Int, p1: Int, p2: Any?) {
                super.afterEvent(p0, p1, p2)
                //回调成功
                if (p1 == SMSSDK.RESULT_COMPLETE) {
                    //获取验证码成功
                    if (p0 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        thread {
                            activity?.runOnUiThread {
                                context?.let { it1 ->
                                    "验证码已发送".makeToast(it1)
                                }
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_codeFragment)
                            }
                        }


                    }
                } else {
                    (p2 as Throwable).printStackTrace()
                }
            }
        }


        //注册事件
        SMSSDK.registerEventHandler(handler)

        fragment_login_enter.setOnClickListener {
            tel = fragment_login_tel.text.toString()
            //如果检查通过了便
            if (context?.let { it1 -> StringUtils.checkPhoneNumber(it1, tel) }!!) {
                userInfoViewModel?.tel?.value = tel
                SMSSDK.getVerificationCode("86", tel)

            }

        }
    }


}