package com.example.hotel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rooms(
    @PrimaryKey var id: String, var name: String,
    var nbr_singleBeds: Int,
    val nbr_persons: Int,
    var status: String,
    val nbr_doubleBeds: Int,
    val type: String
)

