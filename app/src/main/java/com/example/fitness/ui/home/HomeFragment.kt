package com.example.fitness.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.fitness.R
import com.example.fitness.ui.userInfo.UserInfoViewModel
import com.example.fitness.utli.BodyUtlis
import java.util.*

class HomeFragment : Fragment() {
    var map = mapOf<String,String>("偏瘦" to "#bfbfbf","正常" to "#1afa29","过重" to "#f4ea2a","肥胖" to "#ea9518")

    private lateinit var homeViewModel: HomeViewModel

    //获取viewmodel中的user信息
    private val userInfoViewModel by lazy { activity?.let { ViewModelProvider(it).get(UserInfoViewModel::class.java) } }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val BMI : TextView = view.findViewById(R.id.fragment_home_BMI)
        val health : TextView = view.findViewById(R.id.fragment_home_judge_health)

        userInfoViewModel?.user?.observe(viewLifecycleOwner, Observer {
            val height = it.height
            val weight = it.weight
            val countBMI=BodyUtlis.countBMI(height,weight)
            BMI.text = Formatter().format("%.1f",countBMI).toString()
            val state = BodyUtlis.judgeByBMI(countBMI)
            health.text =state
            health.setTextColor(Color.parseColor(map[state]))
        })
    }
}