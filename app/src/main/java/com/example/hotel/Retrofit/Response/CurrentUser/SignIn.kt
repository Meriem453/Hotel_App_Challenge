package com.example.hotel.Retrofit.Response.CurrentUser

data class SignIn(
    val token: String,
    val user: User
)