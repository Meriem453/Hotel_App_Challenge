package com.example.hotel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Guest(@PrimaryKey var id: String, var name:String, var nbr_bookings: Int)
