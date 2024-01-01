package com.example.hotel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.auth0.android.jwt.JWT
import com.example.hotel.Retrofit.Request.Login
import com.example.hotel.Retrofit.RetrofitLogin
import com.google.gson.Gson
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val button = findViewById<Button>(R.id.button)
        val email=findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val login = RetrofitLogin.instance
        button.setOnClickListener {
            val sharedPreferences: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)

            lifecycleScope.launch {

                try{
                    //get the token
                    val response=login.login(Login(email.text.toString(),password.text.toString()))
                    val token=response.body()?.token

                    //put them in sp

                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("token", token)
                    editor.apply()
                //decode the token
                    /*val userId=decodeJwt(token!!)
                    editor.putInt("id",userId.toInt())
                    editor.apply()*/

                    startActivity(Intent(baseContext,MainActivity::class.java))
                    finish()

                }catch (e:Exception){
                    Toast.makeText(baseContext,"Failed! Try again",Toast.LENGTH_LONG).show()
                }}
            }



        }


    fun decodeJwt(token: String):String{

        val jwt = JWT(token)
        jwt.getIssuer()?.let { Log.d("issuer", it) }
        //val issuer: String = jwt.getIssuer() //get registered claims

       return jwt.getClaim("id").toString()



    }
}