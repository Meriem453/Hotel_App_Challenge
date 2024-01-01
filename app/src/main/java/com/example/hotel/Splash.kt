package com.example.hotel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPreferences: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
            if(sharedPreferences.getString("token",null) != null){
                startActivity(Intent(baseContext,MainActivity::class.java))
                finish()

            }else{
            startActivity(Intent(baseContext,Login::class.java))
            finish()}

        }, 3000)

    }
}