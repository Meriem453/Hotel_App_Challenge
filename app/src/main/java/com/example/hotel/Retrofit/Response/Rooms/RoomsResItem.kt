package com.example.hotel.Retrofit.Response.Rooms

data class RoomsResItem(
    val __v: Int,
    val _id: String,
    val name: String,
    val nbr_doubleBeds: Int,
    val nbr_persons: Int,
    val nbr_singleBeds: Int,
    val status: String,
    val type: String
)