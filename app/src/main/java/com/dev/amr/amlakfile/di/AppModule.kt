package com.dev.amr.amlakfile.di

import android.app.Application
import androidx.room.Room
import com.dev.amr.amlakfile.data.db.DBRoom
import com.dev.amr.amlakfile.data.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun provideApi() : Api = Retrofit.Builder()
//        .baseUrl(Const.URL_NESHAN)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build().create(Api::class.java)
//
//    @Provides
//    @Singleton
//    fun provideDataBase(app : Application) : DBRoom =
//        Room.databaseBuilder(app,DBRoom::class.java,"amlak_file.db").fallbackToDestructiveMigration().build()
}