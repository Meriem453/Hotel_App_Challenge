package com.example.hotel.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hotel.R
import com.example.hotel.ViewModel
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Users(vm:ViewModel) {
    Column(Modifier.fillMaxWidth().padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        //vm.getAllUsers()
       // Toast.makeText(LocalContext.current,vm.filteredUser.size,Toast.LENGTH_SHORT).show()
        LazyColumn(
            Modifier
                .padding(20.dp)
                .fillMaxSize()){

            itemsIndexed(vm.filteredUser){position,user->
                        Column(Modifier.fillMaxWidth()) {


                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 10.dp)) {
                    Box (modifier = Modifier.clip(CircleShape)){


                    GlideImage(model = user.picture, contentDescription ="", modifier = Modifier
                        .height(70.dp)
                        .width(70.dp)
                        .clip(CircleShape)
                        ,  contentScale = ContentScale.Crop )}
                    Text(text = "${user.nom} ${user.name}", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(start = 10.dp))
                    Text(text = user.status, fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(start = 30.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                        Icon(painter = painterResource(id = R.drawable.cancel), contentDescription ="", tint = Color.Gray, modifier = Modifier.clickable {
                            vm.deleteUser(user)
                        } )
                    }


                }
                        Divider(modifier = Modifier.fillMaxWidth().padding(top =  5.dp).height(1.dp), color = Color.LightGray,)
                        }

            }
    }}}

@Composable
fun Title(text:String){
    Text(text = text,
        fontSize = 35.sp,
        color = colorResource(id = R.color.blue),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()

        )}
