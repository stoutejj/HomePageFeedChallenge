package com.example.homepagefeedchallenge.data.network

import com.example.homepagefeedchallenge.BuildConfig
import com.example.homepagefeedchallenge.data.model.HomePageResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

const val BASE_URL="https://private-8ce77c-tmobiletest.apiary-mock.com/"
interface WebServices {
    companion object {
        val instance: WebServices by lazy {
            val logging = HttpLoggingInterceptor()
            if(BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            }
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(WebServices::class.java)
        }
    }

    @GET("/test/home")
    fun fetchHomePage(): Single<HomePageResponse>
}