package com.example.hotel

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hotel.data.User
import com.example.hotel.screens.Booking
import com.example.hotel.screens.Dashboard
import com.example.hotel.screens.Rooms
import com.example.hotel.screens.Users

import com.example.hotel.ui.theme.HotelTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelTheme {
                val context= LocalContext.current
                val viewModel= hiltViewModel<ViewModel>()
                val items = listOf(
                    NavigationItem(
                        title = "Dashboard",
                        selectedIcon = painterResource(id = R.drawable.list),

                    ),
                    NavigationItem(
                        title = "Booking",
                        selectedIcon = painterResource(id = R.drawable.notepad),

                    ),
                    NavigationItem(
                        title = "Users",
                        selectedIcon =painterResource(id = R.drawable.users),

                    ),
                    NavigationItem(
                        title = "Room",
                        selectedIcon = painterResource(id = R.drawable.buildings),

                        ),
                    NavigationItem(
                        title = "Log out",
                        selectedIcon = painterResource(id = R.drawable.signout),

                        ),
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    var selectedItemIndex by rememberSaveable {
                        mutableStateOf(0)
                    }
                    var addRoom by remember {
                        mutableStateOf(false)
                    }
                    var addUser by remember {
                        mutableStateOf(false)
                    }
                    ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet() {
                                Spacer(modifier = Modifier.height(16.dp))
                                Column(Modifier.fillMaxWidth()) {
                                    Image(painter = painterResource(id = R.drawable.logo), contentDescription ="", contentScale = ContentScale.Crop, modifier = Modifier
                                        .padding(20.dp)
                                        .height(30.dp) )
                                    Row (
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 20.dp)
                                            ){
                                        Box (modifier = Modifier.clip(CircleShape)){


                                            GlideImage(model = viewModel.user.picture, contentDescription ="", modifier = Modifier
                                                .padding(start = 10.dp, bottom = 10.dp)
                                                .height(70.dp)
                                                .width(70.dp)
                                                .clip(CircleShape)
                                                ,  contentScale = ContentScale.Crop )}
                                        Column( verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                                            Text(text = viewModel.user.name,
                                                color = colorResource(id = R.color.blue),
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(start=10.dp)

                                                )
                                            Text(text = viewModel.user.status,
                                                color = Color.Gray,
                                                fontSize = 14.sp,
                                                modifier = Modifier.padding(start=10.dp)

                                            )
                                        }
                                    }
                                    
                                }
                                items.forEachIndexed { index, item ->
                                    NavigationDrawerItem(
                                        label = {
                                            Text(text = item.title)
                                        },
                                        selected = index == selectedItemIndex,
                                        onClick = {
//                                            navController.navigate(item.route)
                                            selectedItemIndex = index
                                            scope.launch {
                                                drawerState.close()
                                            }
                                        },
                                        icon = {
                                            Icon(

                                                    item.selectedIcon,

                                                contentDescription = item.title
                                            )
                                        },

                                        modifier = Modifier
                                            .padding(NavigationDrawerItemDefaults.ItemPadding),

                                    )
                                }
                                Text(text = "Async data between local storage and database", color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center ,modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp))

                                Button(onClick = { viewModel.async() }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                                    id = R.color.blue
                                )), modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                              Text(text = "Async", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                                }
                            }
                        },
                        drawerState = drawerState
                    ) {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = {
                                        Text(text =  when(selectedItemIndex){
                                            0->{"Dashboard"}
                                            1->{"Booking"}
                                            2->{"Users"}
                                            3->{"Rooms"}
                                            else -> {""}
                                        }, color = colorResource(id = R.color.blue))
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            scope.launch {
                                                drawerState.open()
                                            }
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.Menu,
                                                contentDescription = "Menu"
                                            )
                                        }
                                    }
                                )
                            }, floatingActionButton = {
                                when(selectedItemIndex){
                                    2->{
                                        if(viewModel.user.status=="Super"){
                                        FloatingActionButton(onClick = {
                                        addUser=true
                                    },
                                        containerColor = colorResource(id = R.color.blue),
                                        modifier = Modifier.padding(20.dp),
                                        shape = RoundedCornerShape(50.dp)

                                    ) {

                                        Icon(imageVector = Icons.Default.Add,
                                            contentDescription ="",
                                            tint = Color.White
                                        )
                                    }
                                    }}
                                    3->{

                                            FloatingActionButton(onClick = {
                                                addRoom=true
                                            },
                                                containerColor = colorResource(id = R.color.blue),
                                                modifier = Modifier.padding(20.dp),
                                                shape = RoundedCornerShape(50.dp)

                                            ) {

                                                Icon(imageVector = Icons.Default.Add,
                                                    contentDescription ="",
                                                    tint = Color.White
                                                )
                                            }

                                    }
                                }
                            }
                        ) {
it
                            Column(Modifier.fillMaxSize()) {
                                if(selectedItemIndex!=0){
                                var search by remember {
                                    mutableStateOf("")
                                }
                                Card (
                                    Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 10.dp, top = 70.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                    , border = BorderStroke(1.dp, Color.LightGray)
                                ){
                                    TextField(
                                        value = search,
                                        onValueChange = {
                                            search = it
                                            viewModel.updateSearch(it,selectedItemIndex)

                                        },
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.White), colors = TextFieldDefaults.colors(
                                                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White
                                            ),
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Search,
                                                contentDescription = "search",
                                                tint = Color.LightGray
                                            )
                                        },

                                        )
                                }}
                                when(selectedItemIndex){
                                    0->{Dashboard(viewModel)}
                                    1->{Booking(viewModel)}
                                    2->{Users(viewModel)}
                                   3->{Rooms(viewModel)}


                                }
                            }

                            if(addUser) {
                                Dialog(onDismissRequest = { addUser=false }) {
                                    var nom by remember { mutableStateOf("") }
                                    var prenom by remember { mutableStateOf("") }
                                    var email by remember { mutableStateOf("") }
                                    var password by remember { mutableStateOf("") }
                                    var superr by remember { mutableStateOf(false) }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(
                                        Color.White)) {
                                        Text(text = "Add User", fontSize = 20.sp, color = colorResource(id = R.color.blue), modifier = Modifier.padding(top = 30.dp, bottom = 30.dp))
                                        TextField(value = prenom,
                                            onValueChange ={
                                                prenom=it },
                                            placeholder = {
                                                Text(text = "First Name")
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()

                                                .padding(10.dp)
                                            ,
                                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray)

                                        )
                                        TextField(value = nom,
                                            onValueChange ={nom=it},
                                            placeholder = {
                                                Text(text = "Last Name")
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp)
                                            ,
                                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray)

                                        )
                                        TextField(value = email,
                                            onValueChange ={email=it},
                                            placeholder = {
                                                Text(text = "Email")
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp)
                                            ,
                                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray)

                                        )
                                        TextField(value = password,
                                            onValueChange ={nom=it},
                                            placeholder = {
                                                Text(text = "Password")
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp)
                                            ,
                                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray)

                                        )
                                        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                                            RadioButton(selected =superr ,
                                                onClick = { superr=true })
                                            Text(text = "Super",
                                                fontSize = 14.sp,
                                                color = Color.Gray,
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .weight(1f),

                                            )

                                            RadioButton(selected =!superr ,
                                                onClick = { superr=false })

                                            Text(text = "Normal",
                                                fontSize = 14.sp,
                                                color = colorResource(id = R.color.blue),
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .weight(1f),

                                            )
                                        }
                                        Row (
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                                            Button(onClick = {
                                                viewModel.addUser(User(
                                                    "0",prenom,nom,if(superr) "Super" else "Normal","",LocalDate.now().toString(),email,password
                                                ))
                                                Toast.makeText(context,"User added succefully",Toast.LENGTH_SHORT).show()
                                                addUser=false
                                            }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                                                id = R.color.blue
                                            )), modifier = Modifier) {
                                                Text(text = "Add", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                                            }
                                            Button(onClick = { addUser=false}, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                                                Text(text = "Cancel", color = colorResource(id = R.color.blue), fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                                            }
                                        }

                                    }
                                }
                            }
                            if(addRoom){
                                Dialog(onDismissRequest = { addRoom=false }) {
                                    var name by remember { mutableStateOf("") }
                                    var isSuite by remember { mutableStateOf(false) }
                                    var nbr_persons by remember { mutableStateOf("") }
                                    var nbr_singleBeds by remember { mutableStateOf("") }
                                    var nbr_doubleBeds by remember { mutableStateOf("") }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(
                                        Color.White)) {
                                        Text(text = "Add Room", fontSize = 20.sp, color = colorResource(id = R.color.blue), modifier = Modifier.padding(top = 30.dp, bottom = 30.dp))
                                        TextField(value = name,
                                            onValueChange ={
                                                name=it },
                                            placeholder = {
                                                Text(text = "Number")
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()

                                                .padding(10.dp)
                                            ,
                                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray)

                                        )
                                        TextField(value = nbr_persons.toString(),
                                            onValueChange ={
                                                nbr_persons= it },
                                            placeholder = {
                                                Text(text = "Person's Number")
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
                                                TextField(value = nbr_singleBeds.toString(),
                                                    onValueChange ={
                                                        nbr_singleBeds=it },
                                                    placeholder = {
                                                        Text(text = "Single Beds")
                                                    }
                                                    , keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray),
                                                     modifier = Modifier
                                                        .padding(end = 10.dp), maxLines = 1
                                                )
                                            }
                                            Box(modifier = Modifier.weight(1f)) {
                                                TextField(value = nbr_doubleBeds.toString(),
                                                    onValueChange ={
                                                        nbr_doubleBeds=it },
                                                    placeholder = {
                                                        Text(text = "Double Beds")
                                                    }
                                                    , keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray),
                                                    modifier = Modifier
                                                        .padding(end = 10.dp), maxLines = 1
                                                )
                                            }

                                        }
                                        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                                            RadioButton(selected =isSuite ,
                                                onClick = { isSuite=true })
                                            Text(text = "Suite",
                                                fontSize = 14.sp,
                                                color = Color.Gray,
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .weight(1f),

                                                )

                                            RadioButton(selected =!isSuite ,
                                                onClick = { isSuite=false })

                                            Text(text = "Room",
                                                fontSize = 14.sp,
                                                color = colorResource(id = R.color.blue),
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .weight(1f),

                                                )
                                        }
                                        Row (
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                                            Button(onClick = {viewModel.addRoom(
                                                com.example.hotel.data.Rooms(Random.nextInt().toString(), name,nbr_singleBeds.toInt(),nbr_persons.toInt(),"Available",nbr_doubleBeds.toInt(), if(isSuite) "Suite" else "Room")
                                            )
                                                Toast.makeText(context,"Room added succefully",Toast.LENGTH_SHORT).show()
                                                addRoom=false
                                            }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                                                id = R.color.blue
                                            )), modifier = Modifier) {
                                                Text(text = "Confirm", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                                            }
                                            Button(onClick = { addRoom=false}, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                                                Text(text = "Cancel", color = colorResource(id = R.color.blue), fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                                            }
                                        }
                                    }
                                }

                            }

                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HotelTheme {

    }
}