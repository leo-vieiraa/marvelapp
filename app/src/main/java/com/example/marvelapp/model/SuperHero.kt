package com.example.marvelapp.model

import java.io.Serializable

data class SuperHero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: SuperHeroThumbnail,
    val resourceURI: String
) : Serializable

data class DataResponse(
    val code: Int,
    val data: SuperHeroList
) : Serializable

data class SuperHeroList(
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<SuperHero>
) : Serializable

data class SuperHeroThumbnail(
    val path: String,
    val extension: String
) : Serializable