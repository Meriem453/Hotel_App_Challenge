package com.example.hotel.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hotel.data.Booking
import com.example.hotel.data.Guest
import com.example.hotel.data.Rooms
import com.example.hotel.data.User
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user ")
    fun getUserById():Flow<User>

    @Insert
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE FROM user")
    suspend fun deleteUsers()

    @Query("SELECT * FROM rooms")
    fun getAllRooms(): Flow<List<Rooms>>

    @Insert
    suspend fun insertRooms(rooms: List<Rooms>)

    @Query("DELETE FROM rooms")
    suspend fun deleteRooms()

    @Query("SELECT * FROM guest")
    fun getAllGuests(): Flow<List<Guest>>

    @Insert
    suspend fun insertGuests(guests: List<Guest>)

    @Query("DELETE FROM guest")
    suspend fun deleteGuests()

    @Query("SELECT * FROM booking")
    fun getAllBookings(): Flow<List<Booking>>

    @Insert
    suspend fun insertBookings(bookings: List<Booking>)

    @Query("DELETE FROM booking")
    suspend fun deleteBookings()

}