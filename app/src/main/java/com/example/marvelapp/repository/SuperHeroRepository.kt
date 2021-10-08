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

    suspend fun fetchSuperHeroes(query: String?): List<SuperHero>? {
        return withContext(Dispatchers.Default) {

            val results = service.fetchSuperHeroes(query = query)

            val processedResponse = processData(results)
            processedResponse?.data?.results
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

}