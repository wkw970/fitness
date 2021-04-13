package com.example.fitness.ui.userInfo

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bumptech.glide.Glide
import com.example.fitness.R
import com.example.fitness.logic.network.UserInfoNetwork
import com.example.fitness.logic.service.UserInfoService
import com.example.fitness.utli.FileUtils
import kotlinx.android.synthetic.main.fragment_userinfo_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class UserInfoEditFragment : Fragment() {
    val userInfoViewModel by lazy { activity?.let { ViewModelProvider(it).get(UserInfoViewModel::class.java) } }


    private val REQUEST_CAMER = 1
    private val REQUEST_IMAGE = 2
    lateinit var imageView: ImageView
    private lateinit var userInfo: userInfo
    private val genders: MutableList<String> = arrayListOf("男","女")
    private var selectedGender = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_userinfo_edit, container, false)
        imageView = root.findViewById(R.id.userInfo_edit_camera)
        Glide.with(this).load(R.drawable.camera).centerCrop().into(imageView)
        userInfo = userInfoViewModel?.user?.value as userInfo
        //空值判断
        userInfoViewModel?.user?.observe(viewLifecycleOwner, Observer {
            userInfo_edit_gender.setText(it.gender)
            userInfo_edit_nickname.setText(it.nickname)

        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //调用摄像头或者是相册取选取头像
        userInfo_edit_icon.setOnClickListener {
            userAlbum()
        }

        //
        userInfo_edit_save.setOnClickListener {
            val nickname = userInfo_edit_nickname.toString()
            userInfoViewModel?.setNickname(nickname)
            Navigation.findNavController(view).navigate(R.id.action_navigation_userInfo_edit_to_navigation_userInfo)
        }

    }

    private fun userAlbum() {
        //打开文件选择器
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        //指定只显示图片
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_IMAGE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let {
                        imageView.setImageURI(it)
                        userInfoViewModel?.setIsChange(true)
                        userInfoViewModel?.setIconUri(it)
                    }
                }

            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun uriToFile(uri: Uri) = File(context?.let { FileUtils.getPath(it,uri) })


}