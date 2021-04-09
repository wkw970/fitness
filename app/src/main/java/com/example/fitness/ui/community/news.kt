package com.example.fitness.ui.community

//动态数据类
data class news(
    val newsId: String,//该动态ID 主键
    val ownerId: String,//发布动态的人
    val content: String,  //发布的内容
    val isGraph: Boolean, //是否存在图片
    val likes: Int,//点赞数
    val comments: Int//评论数
)

//动态评论数据类
//评论不支持图片内容
data class newsContent(
    val newsId: String,//依赖于news 作为外键
    val commentUserId: String,//评论用户的ID 依赖于userInfo表
    val content: String,//评论内容
    val likes: Int //点赞数
)