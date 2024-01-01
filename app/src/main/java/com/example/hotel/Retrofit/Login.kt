package com.example.hotel.Retrofit

import com.example.hotel.Retrofit.Request.Login
import com.example.hotel.Retrofit.Response.CurrentUser.SignIn
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("signInApp/")
    suspend fun login(@Body login:Login): Response<SignIn>
}