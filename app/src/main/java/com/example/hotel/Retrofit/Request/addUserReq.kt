package com.example.hotel.Retrofit.Request

import com.google.gson.annotations.SerializedName

data class addUserReq(
    @SerializedName("token")var token: String,
    @SerializedName("nom")var nom:String,
    @SerializedName("prenom")var prenom:String,
    @SerializedName("email")var email:String,
    @SerializedName("password")var password:String,
)
