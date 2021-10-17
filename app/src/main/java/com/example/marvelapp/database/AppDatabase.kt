package com.example.marvelapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvelapp.database.dao.SuperHeroDao
import com.example.marvelapp.model.SuperHero


@Database(
    entities = [SuperHero::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getSuperHeroDao(): SuperHeroDao

    companion object {

        fun getDatabase(context: Context): AppDatabase {

            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "super_hero_db"
            )
                .fallbackToDestructiveMigrationOnDowngrade()
                .build()

        }

    }

}