package com.example.hotel.Retrofit

import com.example.hotel.Retrofit.Request.Token
import com.example.hotel.Retrofit.Request.addBookingReq
import com.example.hotel.Retrofit.Request.addRoomReq
import com.example.hotel.Retrofit.Request.addUserReq
import com.example.hotel.Retrofit.Request.chBookingStatReq
import com.example.hotel.Retrofit.Request.deleteUserReq
import com.example.hotel.Retrofit.Response.Bookings.BookingsResItem
import com.example.hotel.Retrofit.Response.Guests.GuestsResItem
import com.example.hotel.Retrofit.Response.Rooms.RoomsResItem
import com.example.hotel.Retrofit.Response.Users.Users
import com.example.hotel.Retrofit.Response.Users.UsersItem
import com.example.hotel.Retrofit.Response.nbrBooking
import com.example.hotel.Retrofit.Response.nbrDone

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

@POST("roomsApp/addBooking/")
suspend fun addBooking(@Body addBookingReq: addBookingReq)

@POST("dashboardApp/nbrBookings/")
suspend fun getNbrBookings(@Body token: Token):nbrBooking

@POST("dashboardApp/nbrDone/")
suspend fun getNbrDone(@Body token: Token):nbrDone

 @POST("usersApp/addUser/")
 suspend fun addUser(@Body addUserReq: addUserReq)

 @POST("usersApp/deleteUser/")
 suspend fun deleteUser(@Body deleteUserReq: deleteUserReq)

 @POST("generalApp/loyal/")
 suspend fun getLoyal(@Body token: Token):List<GuestsResItem>

 @POST("bookingApp/changeStatus")
 suspend fun changeBookingStatus(@Body chBookingStatReq: chBookingStatReq)


}