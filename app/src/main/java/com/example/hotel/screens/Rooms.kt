package com.example.hotel.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hotel.R
import com.example.hotel.Retrofit.Request.addBookingReq


import com.example.hotel.vm.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Rooms(vm: ViewModel) {
    val context= LocalContext.current
    var showAdd by remember {
        mutableStateOf(false) 
    }
    
    vm.getAllRooms()
LazyColumn(modifier = Modifier
    .fillMaxWidth()
    .padding(top = 10.dp)){
    itemsIndexed(vm.filteredRooms){position,room ->
        Row (
            Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .shadow(2.dp, shape = RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)

            , horizontalArrangement = Arrangement.SpaceEvenly){
Column(){
    Text(text = "Room ${room.name}", fontSize = 16.sp, color = Color.Black, modifier = Modifier.padding( 20.dp))
    Text(text = if(room.status=="available")"Available" else "Not Available", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(20.dp))


}
Column {
    Text(text = "${room.nbr_persons} persons", fontSize = 16.sp, color = colorResource(id = R.color.blue), modifier = Modifier.padding( 20.dp))

    Text(text = "${room.nbr_singleBeds+(room.nbr_doubleBeds * 2)} Beds".toString(), fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding( 20.dp))

}

            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxHeight()) {
                Button(onClick = {showAdd=true }, modifier = Modifier.padding(top=20.dp, bottom = 20.dp),colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                    id = R.color.blue
                ))) {
                    Text(text = "Booking", color = Color.White, fontSize = 14.sp, maxLines = 1)
                }
            }
        }

    }
}
    
    if (showAdd){
        Dialog(onDismissRequest = { showAdd=false }) {
            var name by remember { mutableStateOf("") }
            var room by remember { mutableStateOf("") }
            var start by remember { mutableStateOf("") }
            var end by remember { mutableStateOf("") }
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(
                Color.White)) {
                Text(text = "Add Booking", fontSize = 20.sp, color = colorResource(id = R.color.blue), modifier = Modifier.padding(top = 30.dp, bottom = 30.dp))
                TextField(value = name,
                    onValueChange ={
                        name=it },
                    placeholder = {
                        Text(text = "Name")
                    },
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(10.dp)
                    ,
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray)

                )
                TextField(value = room.toString(),
                    onValueChange ={
                        room=it},
                    placeholder = {
                        Text(text = "Room")
                    },
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(10.dp)
                    , keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray)

                )
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                    Box(modifier = Modifier.weight(1f)) {
                        TextField(value = start,
                            onValueChange ={
                                start=it },
                            placeholder = {
                                Text(text = "Start")
                            }

                            ,
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray),
                            trailingIcon = { Icon(painter = painterResource(id = R.drawable.calendar), contentDescription ="" )}
                            , modifier = Modifier
                                .padding(end = 10.dp), maxLines = 1
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        TextField(value = end,
                            onValueChange ={
                                end=it },
                            placeholder = {
                                Text(text = "End")
                            }

                            ,
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray),
                            trailingIcon = { Icon(painter = painterResource(id = R.drawable.calendar), contentDescription ="" )}

                        )
                    }

                }
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                    Button(onClick = {vm.addBooking(addBookingReq(name,room,start,end,""))
                                     Toast.makeText(context,"Booking added successfully",Toast.LENGTH_SHORT).show()
                        showAdd=false
                                     }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                        id = R.color.blue
                    )), modifier = Modifier) {
                        Text(text = "Confirm", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                    }
                    Button(onClick = { showAdd=false}, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                        Text(text = "Cancel", color = colorResource(id = R.color.blue), fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                    }
                }
            }
        }
    }
}


