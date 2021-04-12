package com.example.fitness.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//单例类用来创建动态代理
object ServiceCreator {
    private const val BASE_URL ="http://192.168.137.1:8888" //访问的远程服务端IP地址

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass :Class<T>):T = retrofit.create(serviceClass)

    //内联函数用来将类实例化 调用上面的create函数,生成动态代理使得可以调用接口中的方法
    inline fun <reified T> create():T = create(T::class.java)
}