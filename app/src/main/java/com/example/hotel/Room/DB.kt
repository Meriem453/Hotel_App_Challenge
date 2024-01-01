package com.example.hotel.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hotel.data.Booking
import com.example.hotel.data.Guest
import com.example.hotel.data.Rooms
import com.example.hotel.data.User


    @Database(entities = [User::class,Guest::class,Booking::class,Rooms::class], version = 1)
    abstract class DB : RoomDatabase() {
        abstract fun dao(): Dao
    }
