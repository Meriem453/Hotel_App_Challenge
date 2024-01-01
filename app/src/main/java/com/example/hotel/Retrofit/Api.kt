package com.example.hotel.Retrofit

import com.example.hotel.Retrofit.Request.Token
import com.example.hotel.Retrofit.Request.addRoomReq
import com.example.hotel.Retrofit.Response.Bookings.BookingsResItem
import com.example.hotel.Retrofit.Response.Guests.GuestsResItem
import com.example.hotel.Retrofit.Response.Rooms.RoomsResItem
import com.example.hotel.Retrofit.Response.Users.Users
import com.example.hotel.Retrofit.Response.Users.UsersItem

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
@POST("usersApp/")
 suspend fun getAllUsers(@Body token:Token):List<UsersItem>

 @POST("bookingApp/")
 suspend fun getAllBookings(@Body token:Token):List<BookingsResItem>

@POST("roomsApp/")
suspend fun getAllRooms(@Body token:Token):List<RoomsResItem>

@POST("dashboardApp/guests/")
suspend fun getAllGests(@Body token: Token):List<GuestsResItem>

@POST("roomsApp/addRoom/")
suspend fun addRoom(@Body addRoomReq: addRoomReq)



//@POST("roomsApp/addBooking/")


}