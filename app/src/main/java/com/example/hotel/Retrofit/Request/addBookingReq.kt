package com.example.hotel.Retrofit.Request

import com.google.gson.annotations.SerializedName

data class addBookingReq(
    @SerializedName("name")var name:String,
    @SerializedName("room")var room:String,
    @SerializedName("start_date")var start_date:String,
    @SerializedName("end_date")var end_date:String,
    @SerializedName("token")var token:String

    )
