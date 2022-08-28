package com.example.marvelapp.repository

import com.example.marvelapp.database.dao.SuperHeroDao
import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.services.MarvelApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SuperHeroRepository @Inject constructor(
    private val service: MarvelApi,
    private val superHeroDao: SuperHeroDao
) {

    suspend fun fetchSuperHeroes(query: String?, limit: Int?, offset:Int?): List<SuperHero>? {
        return withContext(Dispatchers.Default) {

            val results = service.fetchSuperHeroes(query, limit, offset)

            val processedResponse = processData(results)
            insert(processedResponse!!.data.results)
            processedResponse.data.results
        }
    }

    suspend fun insert(heroList: List<SuperHero>): Boolean {
        return withContext(Dispatchers.Default) {
            superHeroDao.insert(heroList)
            true
        }
    }

    suspend fun fetchFromDB(): List<SuperHero> {
        return withContext(Dispatchers.Default){
            superHeroDao.fetch()
        }
    }

    suspend fun fetchFromDB(query: String?): List<SuperHero> {
        return withContext(Dispatchers.Default){
            superHeroDao.fetchByName(query)
        }
    }

    suspend fun fetchFromDB(query: String?, limit: Int): List<SuperHero> {
        return withContext(Dispatchers.Default){
            superHeroDao.fetchFeaturedHeroByName(query, limit)
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

}