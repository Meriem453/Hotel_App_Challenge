package com.example.hotel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.Retrofit.Api
import com.example.hotel.Retrofit.Request.Token
import com.example.hotel.Retrofit.RtrofitClient
import com.example.hotel.data.Booking
import com.example.hotel.data.Guest
import com.example.hotel.data.Rooms
import com.example.hotel.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val rep:Repository,
    private val c:Application,
    private val pref:SharedPreferences
): ViewModel() {
    var user by mutableStateOf(User("0","Meriem","Zemane","Super","https://firebasestorage.googleapis.com/v0/b/residence-47d76.appspot.com/o/images%2F7s28g4ICpPWBmrjKBgwLVfzCSTz2.jpg?alt=media&","04/05/2003","fdhb@dfsh.com","123456"),
    )
    lateinit var retrofit:Api
    var token by mutableStateOf("")
     //users
    var users by mutableStateOf(emptyList<User>())
    var filteredUser by mutableStateOf(emptyList<User>())
    //rooms
    var rooms by mutableStateOf(emptyList<Rooms>())
    var filteredRooms by mutableStateOf(emptyList<Rooms>())
    //bookings
    var bookings by mutableStateOf(emptyList<Booking>())
    var filteredBookings by mutableStateOf(emptyList<Booking>())
    //guests
    var guests by mutableStateOf(emptyList<Guest>())
    var filteredGuests by mutableStateOf(emptyList<Guest>())

    fun getLoggedUser(){
        viewModelScope.launch {
         token= pref.getString("token",null)!!
            retrofit=RtrofitClient.create(token)
           /* val id = pref.getInt("id",0)
            rep.getUserById(id).collect{
                user=it
            }*/
        }
    }

    fun getAllUsers(){
        Toast.makeText(c,"get all users",Toast.LENGTH_SHORT).show()
        viewModelScope.launch {
            rep.getAllUsers().collect{
                filteredUser=it
                Toast.makeText(c,filteredUser.size.toString(),Toast.LENGTH_SHORT).show()
                //updateSearch("",2)
            }
        }
    }
    fun getAllRooms(){
        viewModelScope.launch {
            rep.getAllRooms().collect{
                rooms=it
                updateSearch("",3)
            }
        }
    }
    fun getAllBookings(){
        viewModelScope.launch {
            rep.getAllBooking().collect{
                bookings=it
                updateSearch("",1)
            }
        }
    }
    fun getAllGuests(){
        viewModelScope.launch {
            rep.getAllGuests().collect{
                guests=it
                updateSearch("",0)
            }
        }
    }
fun getAllBookingOperations():Long{
    return 120000
}
     fun updateSearch(text:String,page:Int) {
        when(page){
            0->{  filteredGuests=if(text=="") guests else guests.filter { it.name.contains(text,ignoreCase = true) }
            }
            1->{filteredBookings=if(text=="") bookings else bookings.filter { it.room.toString().contains(text,ignoreCase = true) }}
            2->{
                filteredUser=if(text=="") users else users.filter { it.nom.contains(text,ignoreCase = true) }
            }
            3->{filteredRooms=if(text=="") rooms else rooms.filter { it.name.contains(text,ignoreCase = true) }}
        }
    }

    fun deleteUser(user: User) {
viewModelScope.launch {
    rep.deleteUser(user)
}
    }

     fun cancelBooking(booking: Booking){
viewModelScope.launch {
    rep.cancelBooking(booking)
}
    }
    fun acceptBooking(booking: Booking){
viewModelScope.launch {
    rep.acceptBooking(booking)
}
    }
     fun addRoom(room: Rooms){
         viewModelScope.launch {
             rep.addRoom(room)

         }
     }
     fun addBooking(booking: Booking){
viewModelScope.launch {
    rep.addBooking(booking)
}
    }
fun addUser(user: User){

}
    fun getDoneBooking(): Long {
return 2547
    }
    fun async(){
        viewModelScope.launch {
            Log.d("token",token)

            val _users=retrofit.getAllUsers(Token(token))
            val _rooms=retrofit.getAllRooms(Token(token))
            val _guests=retrofit.getAllGests(Token(token))
            val _bookings=retrofit.getAllBookings(Token(token))

            Toast.makeText(c,_users[0].nom,Toast.LENGTH_SHORT).show()
            rep.async(_users,_rooms,_guests,_bookings)



        }



    }
    init {
        getLoggedUser()
        getAllUsers()
    }

}