package com.example.hotel.Retrofit.Request

import com.google.gson.annotations.SerializedName

data class deleteUserReq(
    @SerializedName("token")var token: String,
    @SerializedName("id")var id:String
)
