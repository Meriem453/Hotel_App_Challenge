package com.example.hotel.Retrofit.Request

data class addRoomReq(
    val name: String,
    val nbr_doubleBeds: Int,
    val nbr_persons: Int,
    val nbr_singleBeds: Int,
    val token: String,
    val type: String
)