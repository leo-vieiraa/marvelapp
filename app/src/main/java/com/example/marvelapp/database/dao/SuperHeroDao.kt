package com.example.marvelapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.marvelapp.model.SuperHero

@Dao
interface SuperHeroDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(heroList: List<SuperHero>)

    @Query("SELECT * FROM SuperHero")
    suspend fun fetch() : List<SuperHero>

    @Query("SELECT * FROM SuperHero WHERE name LIKE '%' || :query || '%'")
    suspend fun fetchByName(query: String?) : List<SuperHero>

    @Query("SELECT * FROM SuperHero WHERE name = '' || :query || '' LIMIT '' || :limit || '' ")
    suspend fun fetchFeaturedHeroByName(query: String?, limit: Int) : List<SuperHero>

}