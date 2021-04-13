package com.example.fitness.ui.userInfo

import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide

import com.example.fitness.R
import com.example.fitness.logic.network.ServiceCreator
import kotlinx.android.synthetic.main.fragment_userinfo.*

class UserInfoFragment : Fragment() {

    val userInfoViewModel by lazy { activity?.let { ViewModelProvider(it).get(UserInfoViewModel::class.java) } }
    private lateinit var imageView:ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root  = inflater.inflate(R.layout.fragment_userinfo,container,false)
        imageView= root.findViewById(R.id.fragment_userInfo_icon)


        //观察viewModel中的user信息是否发生变化
        userInfoViewModel?.user?.observe(viewLifecycleOwner, Observer {
            if (it.icon=="") {
                val resource = R.drawable.demoicon
                Glide.with(this).load(resource).centerCrop().into(imageView)
            }
            else{
                Glide.with(this).load(ServiceCreator.BASE_URL+it.icon).centerCrop().circleCrop().into(imageView)
            }
            fragment_userInfo_nickName.text = it.nickname
            fragment_userInfo_display_height.text =it.height.toString()+" cm"
            fragment_userInfo_display_weight.text=it.weight.toString()+" kg"

        })

        userInfoViewModel?.iconUri?.observe(viewLifecycleOwner, Observer {
            if (it!==null)
                Glide.with(this).load(it).centerCrop().circleCrop().into(imageView)
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_userInfo_userInfoLL.setOnClickListener{
            view.let { Navigation.findNavController(it).navigate(R.id.action_navigation_userInfo_to_userInfoEditFragment) }
        }

    }




}