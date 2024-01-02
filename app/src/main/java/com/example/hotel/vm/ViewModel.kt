package com.example.hotel.vm

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.repo.Repository
import com.example.hotel.Retrofit.Api
import com.example.hotel.Retrofit.Request.Token
import com.example.hotel.Retrofit.Request.addBookingReq
import com.example.hotel.Retrofit.Request.addRoomReq
import com.example.hotel.Retrofit.Request.addUserReq
import com.example.hotel.Retrofit.Request.chBookingStatReq
import com.example.hotel.Retrofit.Request.deleteUserReq
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
    private val rep: Repository,
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
            val name = pref.getString("name","User")
            val role = pref.getString("role","normal")
            user= User("",name!!,"",role!!,"https://firebasestorage.googleapis.com/v0/b/residence-47d76.appspot.com/o/images%2F7s28g4ICpPWBmrjKBgwLVfzCSTz2.jpg?alt=media&"
                ,"","","")
        }
    }

    fun getAllUsers(){

        viewModelScope.launch {
            rep.getAllUsers().collect{
                filteredUser=it

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
fun getAllBookingOperations():Int{
    var nbr by mutableStateOf(24)
    viewModelScope.launch {
        try {
            nbr=retrofit.getNbrBookings(Token(token)).nbr
        }catch (e:Exception){
            Toast.makeText(c,"Check your connection",Toast.LENGTH_SHORT).show()
        }


    }

    return nbr
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

    fun deleteUser(deleteUserReq: deleteUserReq) {
        deleteUserReq.token=token
viewModelScope.launch {
    try {
        retrofit.deleteUser(deleteUserReq)
    }catch (e:Exception){
        Toast.makeText(c,"Check your connection",Toast.LENGTH_SHORT).show()
    }

}
    }

     fun cancelBooking(chBookingStatReq: chBookingStatReq){
         chBookingStatReq.token=token
viewModelScope.launch {
    try {
        retrofit.changeBookingStatus(chBookingStatReq)
    }catch (e:Exception){
        Toast.makeText(c,"Check your connection",Toast.LENGTH_SHORT).show()
    }
}
    }
    fun acceptBooking(chBookingStatReq: chBookingStatReq){
        chBookingStatReq.token=token
viewModelScope.launch {
    try {
        retrofit.changeBookingStatus(chBookingStatReq)
    }catch (e:Exception){
        Toast.makeText(c,"Check your connection",Toast.LENGTH_SHORT).show()
    }
}
    }
     fun addRoom(addRoomReq: addRoomReq){
         addRoomReq.token=token
         viewModelScope.launch {
             try {
                 retrofit.addRoom(addRoomReq)
             }catch (e:Exception){
                 Toast.makeText(c,"Check your connection",Toast.LENGTH_SHORT).show()
             }

         }
     }
     fun addBooking(addBookingReq: addBookingReq){
         addBookingReq.token=token
viewModelScope.launch {
    try {
        retrofit.addBooking(addBookingReq)
    }catch (e:Exception){
        Toast.makeText(c,"Check your connection",Toast.LENGTH_SHORT).show()
    }
}
    }
 fun addUser(userReq: addUserReq){
     userReq.token=token
     viewModelScope.launch {
         try {
             retrofit.addUser(userReq)
         }catch (e:Exception){
             Toast.makeText(c,"Check your connection",Toast.LENGTH_SHORT).show()
         }
     }

}
    fun getDoneBooking(): Int {
        var nbr by mutableStateOf(10)
        viewModelScope.launch {
            try {
                nbr=retrofit.getNbrDone(Token(token)).nbr
            }catch (e:Exception){
                Toast.makeText(c,"Check your connection",Toast.LENGTH_SHORT).show()
            }


        }

        return nbr
    }
    fun async(){
        viewModelScope.launch {
            Log.d("token",token)
try {
    val _users=retrofit.getAllUsers(Token(token))
    val _rooms=retrofit.getAllRooms(Token(token))
    val _guests=retrofit.getAllGests(Token(token))
    val _bookings=retrofit.getAllBookings(Token(token))


    rep.async(_users,_rooms,_guests,_bookings)
}catch (e:Exception){
    Toast.makeText(c,"Asynced successfully",Toast.LENGTH_SHORT).show()
}

        }
    }

    fun logOut(){
val edit=pref.edit()
        edit.putString("name",null)
        edit.apply()
        edit.putString("role",null)
        edit.apply()
        edit.putString("token",null)
        edit.apply()
    }

    init {
        getLoggedUser()
    }

}