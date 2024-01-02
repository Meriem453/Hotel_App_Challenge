package com.example.hotel.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.hotel.repo.Repository
import com.example.hotel.Room.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun createRepo(db: DB): Repository {
        return Repository(db)
    }

    @Provides
    @Singleton
    fun createDB(c:Application):DB{
        return Room.databaseBuilder(
            c, DB::class.java, "hotel"
        ).build()
    }
    @Provides
    @Singleton
    fun getSharedPrefernces(c:Application):SharedPreferences{
        return c.getSharedPreferences("pref",Context.MODE_PRIVATE)
    }
}