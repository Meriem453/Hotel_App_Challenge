package com.example.hotel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey var _id: String,
                var name:String, var nom:String, var status:String, var picture: String, var date:String,
                var email:String,
                var password:String,)