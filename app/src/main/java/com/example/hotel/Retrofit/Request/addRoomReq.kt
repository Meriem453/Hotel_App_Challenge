package com.example.hotel.Retrofit.Request

import com.google.gson.annotations.SerializedName

data class addRoomReq(
    @SerializedName("name")var name: String,
    @SerializedName("nbr_doubleBeds")var nbr_doubleBeds: Int,
    @SerializedName("nbr_persons")var nbr_persons: Int,
    @SerializedName("nbr_singleBeds")var nbr_singleBeds: Int,
    @SerializedName("token")var token: String,
    @SerializedName("type")var type: String
)