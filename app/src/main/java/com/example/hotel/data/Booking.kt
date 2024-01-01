package com.example.hotel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Booking(@PrimaryKey val varid: String, var name:String, var status:String, var start_date:String,
                   var end_date:String, var room: String
)

