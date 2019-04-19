package com.ly.kotlinsupermvvm.server

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ly.kotlinsupermvvm.BuildConfig
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * <Pre>
 *     基础retrofit服务
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *
 * Create by 2019/4/18 16:44
 */
abstract class BaseRetrofitServer {
    companion object {
        private const val TIME_OUT = 5
    }

    private val client: OkHttpClient get() {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }else{
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }

        builder.addInterceptor(logging)
            .callTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

        buildClient(builder)

        return RetrofitUrlManager.getInstance().with(builder).build()
    }

    abstract fun buildClient(builder: OkHttpClient.Builder)

    fun <S> createService(serviceClass: Class<S>, baseUrl: String) : S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}