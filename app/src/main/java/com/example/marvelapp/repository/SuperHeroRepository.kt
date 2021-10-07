package com.example.marvelapp.repository

import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.services.MarvelApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SuperHeroRepository @Inject constructor(
    private val service: MarvelApi
) {

    suspend fun fetchSuperHeroes(): List<SuperHero>? {
        return withContext(Dispatchers.Default) {
            val results = service.fetchSuperHeroes()
            val processedResponse = processData(results)
            processedResponse?.results
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

}