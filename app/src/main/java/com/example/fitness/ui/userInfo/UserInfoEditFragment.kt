package com.example.fitness.ui.userInfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fitness.R
import kotlinx.android.synthetic.main.fragment_userinfo_edit.*

class UserInfoEditFragment : Fragment() {
    val userInfoViewModel by lazy { activity?.let { ViewModelProvider(it).get(UserInfoViewModel::class.java) } }


    private val REQUEST_CAMER = 1
    private val REQUEST_IMAGE =2
    lateinit var imageView:ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  root =inflater.inflate(R.layout.fragment_userinfo_edit,container,false)
        imageView = root.findViewById(R.id.userInfo_edit_camera)
        Glide.with(this).load(R.drawable.camera).centerCrop().into(imageView)
        //空值判断
        userInfoViewModel?.user?.observe(viewLifecycleOwner, Observer {
            if (it.gender)
                userInfo_edit_gender.setText("男")
            else
                userInfo_edit_gender.setText("女")

            userInfo_edit_nickname.setText(it.nickname)

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //调用摄像头或者是相册取选取头像
        userInfo_edit_icon.setOnClickListener{
            UserAlbum()
        }


        //点击保存以后将数据上传到服务器端
        userInfo_edit_save.setOnClickListener {

        }

    }

    fun UserAlbum(){
        //打开文件选择器
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        //指定只显示图片
        intent.type ="image/*"
        startActivityForResult(intent,REQUEST_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            REQUEST_IMAGE->{
                if (resultCode == Activity.RESULT_OK && data!=null) {
                  data.data?.let {
                      imageView.setImageURI(it)
                      userInfoViewModel?.setIconUri(it)
                  }
                }

            }
        }
    }








}