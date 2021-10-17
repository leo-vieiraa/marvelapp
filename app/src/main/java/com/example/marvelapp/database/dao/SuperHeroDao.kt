package com.example.marvelapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.marvelapp.model.SuperHero

@Dao
interface SuperHeroDao {

    @Insert(onConflict = IGNORE)
    suspend fun insert(heroList: List<SuperHero>)

    @Query("SELECT * FROM SuperHero WHERE name LIKE '%%'")
    suspend fun fetch() : List<SuperHero>

}