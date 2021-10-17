package com.example.marvelapp.di

import android.content.Context
import com.example.marvelapp.database.AppDatabase
import com.example.marvelapp.database.dao.SuperHeroDao
import com.example.marvelapp.services.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client : OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)

    @Provides
    fun provideMarvelDao(@ApplicationContext context: Context): SuperHeroDao {
        return AppDatabase.getDatabase(context).getSuperHeroDao()
    }

}