package com.example.marvelapp.services

import com.example.marvelapp.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun fetchSuperHeroes(
        @Query("nameStartsWith") query: String?,
        @Query("ts") timestamp: Int = 1633618806,
        @Query("apikey") apikey: String = "466f1761aff313227aef40b7c55f3a61",
        @Query("hash") hash: String = "e2bbe050fd4447a8176393ef4556f083"
    ) : Response<DataResponse>

}