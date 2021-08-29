package com.example.chuculture.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络请求对象的封装
 */
object NetWorkClient {

    private const val BASE_URL="http://rp.dianhsu.top:6060/"
    const val BASE_IMAGE_URL="${BASE_URL}chucultureImage/"
    const val BASE_VIDEO_URL="${BASE_URL}video/"

    //打印网络请求信息
    private val logger = HttpLoggingInterceptor {
        Log.e("HttpInfo", it)
    }

    //构造请求对象
    private val client = OkHttpClient.Builder()
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .addNetworkInterceptor(logger)
        .build()

    //封装网络请求
    fun createNetWorkApi(): NetWorkApi {
        logger.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetWorkApi::class.java)
    }
}