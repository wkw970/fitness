package com.example.fitness.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.R
import com.example.fitness.utli.PickerView
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * 注册页面
 */
class RegisterFragment : Fragment() {
    //创建范围为100-200的身高数组
    private val heightScan: MutableList<String> = mutableListOf()

    //创建范围为40-150
    private val weightScan: MutableList<String> = mutableListOf()

    //创建
    private val genders: MutableList<String> = mutableListOf()

    private var selectedHeight = ""
    private var selectedWeight = ""
    private var selectedGender = ""


    val userInfoViewModel by lazy { activity?.let { ViewModelProvider(it).get(UserInfoViewModel::class.java) } }
    private lateinit var tel: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)
        //初始化目标数组
        for (i in 100 until 200)
            heightScan[i - 100] = i.toString() + "cm"
        for (i in 40 until 150)
            weightScan[i - 40] = i.toString() + "kg"

        genders[0] = "男"
        genders[1] = "女"

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_register_height.setOnClickListener {
            showPickerView(0)
            fragment_register_height.setText(selectedHeight)
        }
        fragment_register_weight.setOnClickListener {
            showPickerView(1)
            fragment_register_weight.setText(selectedWeight)
        }
        fragment_register_gender.setOnClickListener {
            showPickerView(2)
            fragment_register_gender.setText(selectedGender)
        }


    }

    fun showPickerView(chose: Int) {
        when (chose) {
            0 -> {
                context?.let {
                    selectedHeight = PickerView.showGenderSelect(it, heightScan) as String
                }
            }

            1 -> {
                context?.let {
                    selectedWeight = PickerView.showGenderSelect(it, weightScan) as String
                }
            }
            2 -> {
                context?.let { selectedGender = PickerView.showGenderSelect(it, genders) as String }
            }
        }

    }

}