package com.example.hotel

import androidx.room.withTransaction
import com.example.hotel.Retrofit.Response.Bookings.BookingsResItem
import com.example.hotel.Retrofit.Response.Guests.GuestsResItem
import com.example.hotel.Retrofit.Response.Rooms.RoomsResItem
import com.example.hotel.Retrofit.Response.Users.UsersItem
import com.example.hotel.Room.DB
import com.example.hotel.data.Booking
import com.example.hotel.data.Guest
import com.example.hotel.data.Rooms
import com.example.hotel.data.User
import kotlinx.coroutines.flow.Flow

class Repository (private  val db: DB){
    private val dao =db.dao()
    fun getAllUsers(): Flow<List<User>> {
        return dao.getAllUsers()
    }


     fun getAllBooking():Flow<List<Booking>>{
        return dao.getAllBookings()
    }
fun getAllRooms():Flow<List<Rooms>>{
    return dao.getAllRooms()
}
    fun getAllGuests():Flow<List<Guest>>{
        return dao.getAllGuests()
    }
    suspend fun deleteUser(user: User) {

    }
    suspend fun cancelBooking(booking: Booking){

    }
    suspend fun acceptBooking(booking: Booking){

    }
    suspend fun addRoom(room: Rooms) {

    }
    suspend fun addBooking(booking: Booking) {

    }
    suspend fun addUser(user: User) {

    }

    suspend fun async(
        users: List<UsersItem>,
        _rooms: List<RoomsResItem>,
        _guests: List<GuestsResItem>,
        _bookings: List<BookingsResItem>
    ){
        db.withTransaction{
            dao.deleteBookings()
            dao.deleteGuests()
            dao.deleteRooms()
            dao.deleteUsers()
            dao.insertBookings(_bookings.map { Booking(it._id,it.name,it.status,it.start_date,it.end_date,it.room) })
            dao.insertGuests(_guests.map { Guest(it._id,it.name,it.nbr_bookings) })
            dao.insertUsers(users.map { User(it._id,it.prenom,it.nom,it.status,"","",it.email,it.password) })
            dao.insertRooms(_rooms.map { Rooms(it._id,it.name,it.nbr_singleBeds,it.nbr_persons,it.status,it.nbr_doubleBeds,it.type) })
        }
    }
}