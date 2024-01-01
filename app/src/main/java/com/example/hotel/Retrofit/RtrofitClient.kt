package com.example.hotel.Retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RtrofitClient {
    private const val BASE_URL = "https://aceiny.tech:9991/"

    fun create( token:String): Api {
        val client = OkHttpClient.Builder()
        client.interceptors().clear()
        client.addInterceptor { chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                //.header("Authorization", "Bearer $token")
                .build()
            Log.d("request",request.toString())
            val response = chain.proceed(request)
            val responseBody = response.body
            Log.d("response_body", responseBody!!.string())
            response

        }


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.client(client.build())
            .build()

        return retrofit.create(Api::class.java)
    }
}