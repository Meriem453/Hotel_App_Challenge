package com.example.hotel.Retrofit.Request

import com.google.gson.annotations.SerializedName

data class chBookingStatReq(
    @SerializedName("token")var token: String,
    @SerializedName("id")var id: String,
    @SerializedName("status")var status: String,
)
