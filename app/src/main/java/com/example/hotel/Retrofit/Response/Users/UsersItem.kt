package com.example.hotel.Retrofit.Response.Users

data class UsersItem(
    val __v: Int,
    val _id: String,
    val email: String,
    val nom: String,
    val password: String,
    val prenom: String,
    val status: String
)