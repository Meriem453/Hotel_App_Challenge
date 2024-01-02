package com.example.hotel.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hotel.R
import com.example.hotel.vm.ViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Dashboard(vm: ViewModel) {
vm.getAllGuests()
   Column(
       Modifier
           .fillMaxSize()
           .padding(start = 10.dp, end = 10.dp, top = 70.dp)) {
      Box (Modifier.fillMaxWidth()){
          Image(painter = painterResource(id = R.drawable.dashboard), contentDescription = "", modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop)
Row(horizontalArrangement = Arrangement.SpaceEvenly){
    Column(Modifier.weight(1f)) {
        Text(text = vm.getAllBookingOperations().toString(), color = Color.White, textAlign = TextAlign.Center, fontSize = 35.sp, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp))
        Text(text = "Booking Operations", color = Color.White, fontSize = 14.sp,textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())

    }
    Column (Modifier.weight(1f)){
        Text(text = vm.getDoneBooking().toString(), color = Color.White,textAlign = TextAlign.Center, fontSize = 35.sp, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp))
        Text(text = "Done Booking", color = Color.White, textAlign = TextAlign.Center,fontSize = 14.sp,modifier = Modifier.fillMaxWidth())
    }
}


      }
       Text(text = "Loyal Guests", color = colorResource(id = R.color.blue), textAlign = TextAlign.Start, fontSize = 20.sp, modifier = Modifier
           .fillMaxWidth()
           .padding(10.dp))
       LazyRow(modifier = Modifier.padding(start = 10.dp)) {
           itemsIndexed(vm.guests.sortedByDescending { it.nbr_bookings }){pos,guest->
               Card(shape =  RectangleShape, modifier = Modifier
                   .padding(5.dp)
                   .shadow(2.dp, shape = RoundedCornerShape(20.dp))
                   .clip(RoundedCornerShape(20.dp))
                   , colors = CardDefaults.cardColors(containerColor = Color.White)) {
Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(Color.White)) {
    GlideImage(model = vm.user.picture
        , contentDescription ="", modifier = Modifier
            .padding(top = 10.dp, start = 30.dp, end = 30.dp)
            .shadow(2.dp, shape = CircleShape)
            .height(70.dp)
            .width(70.dp)
            .clip(CircleShape)
        ,  contentScale = ContentScale.Crop )
    Text(text = guest.name, fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 10.dp))
}
               } 
           }
           
       }
       Text(text = "All Guests", color = colorResource(id = R.color.blue), textAlign = TextAlign.Start, fontSize = 20.sp, modifier = Modifier
           .fillMaxWidth()
           .padding(10.dp))

       LazyColumn(Modifier.fillMaxWidth()){
itemsIndexed(vm.filteredGuests){position,guest->
    Column(Modifier.fillMaxWidth()) {


        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 10.dp)) {
            Box (modifier = Modifier.clip(CircleShape)){


                GlideImage(model = "https://firebasestorage.googleapis.com/v0/b/residence-47d76.appspot.com/o/images%2F7s28g4ICpPWBmrjKBgwLVfzCSTz2.jpg?alt=media&"
                    , contentDescription ="", modifier = Modifier
                        .height(70.dp)
                        .width(70.dp)
                        .clip(CircleShape)
                    ,  contentScale = ContentScale.Crop )
            }
            Text(text = guest.name, fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(start = 10.dp))
            Text(text = "${guest.nbr_bookings} booking", fontSize = 16.sp, color = Color.Gray, textAlign = TextAlign.End, modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp))



        }
        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .height(1.dp), color = Color.LightGray,)
    }

}
       }
   }
       
    }
    
