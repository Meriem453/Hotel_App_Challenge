package com.example.hotel.Retrofit.Response.CurrentUser

data class User(
    val _id: String,
    val email: String,
    val nom: String,
    val password: String,
    val prenom: String,
    val status: String
)