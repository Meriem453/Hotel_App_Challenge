package com.example.hotel.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hotel.R
import com.example.hotel.Retrofit.Request.chBookingStatReq
import com.example.hotel.vm.ViewModel
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Booking(vm: ViewModel) {
    vm.getAllBookings()
    val context= LocalContext.current
    var showAct by remember {
        mutableStateOf(false)
    }
    var Act by remember {
        mutableStateOf(false)
    }
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 10.dp).background(Color.White)){
        itemsIndexed(vm.filteredBookings){position,booking ->
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .shadow(2.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)



                , horizontalArrangement = Arrangement.SpaceEvenly){
                Column(modifier = Modifier.weight(1f)){
                    Row {
                        Box (modifier = Modifier.clip(CircleShape)){


                            GlideImage(model = booking.name, contentDescription ="", modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                                .clip(CircleShape)
                                .padding(start = 10.dp, top = 10.dp)
                                ,  contentScale = ContentScale.Crop )}
                        Text(text = booking.name, fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(10.dp))
                    }
                    Text(text = "${booking.start_date}-->${booking.end_date}", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding( 10.dp))
                }

                Text(text ="Room ${booking.room}", fontSize = 16.sp, color = Color.Black, modifier = Modifier
                    .padding(10.dp)
                    .weight(1f))

                Column(modifier = Modifier.weight(1f)) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                        .background(
                            when (booking.status) {
                                "Pending" -> Color.LightGray
                                "Done" -> colorResource(id = R.color.green)
                                "Canceled" -> colorResource(id = R.color.red)
                                else -> Color.White

                            }
                        )) {
                        Text(text =booking.status, fontSize = 16.sp, color = if(booking.status=="Unverified") Color.Gray else Color.White, textAlign = TextAlign.Center, modifier = Modifier.padding(5.dp))

                    }
                      Row(modifier = Modifier.padding( 10.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                          Icon(painter = painterResource(id = R.drawable.checkfat), contentDescription ="", tint = colorResource(id = R.color.blue), modifier = Modifier.clickable {
                              Act=true
                              showAct=true
                          }.weight(1f) )
                          Icon(painter = painterResource(id = R.drawable.cancel), contentDescription ="", tint = Color.Gray, modifier = Modifier.clickable {
                              Act=false
                              showAct=true
                          }. weight(1f))
                      }
                }
            }
if(showAct){
    Dialog(onDismissRequest = { showAct=false }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(
            Color.White)) {
            Text(text = "Are you sure?", fontSize = 20.sp, color = colorResource(id = R.color.blue), modifier = Modifier.padding(top = 30.dp, bottom = 30.dp))
            Text(text = "Please confirm this operation before we start", fontSize = 16.sp, color = Color.Gray, textAlign = TextAlign.Center,modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                Button(onClick = {
                       if (Act) vm.acceptBooking(chBookingStatReq("",booking.varid,"Done"))
                       else vm.cancelBooking(chBookingStatReq("",booking.varid,"Canceled"))
                    Toast.makeText(context,"", Toast.LENGTH_SHORT).show()
                    showAct=false
                }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                    id = R.color.blue
                )), modifier = Modifier) {
                    Text(text = "Confirm", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                }
                Button(onClick = { showAct=false}, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                    Text(text = "Cancel", color = colorResource(id = R.color.blue), fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                }
            }
        }
    }
}
        }
    }
}