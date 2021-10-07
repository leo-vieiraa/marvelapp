package com.example.marvelapp.model

data class SuperHero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: SuperHeroThumbnail,
    val resourceURI: String
)

data class DataResponse(
    val data: SuperHeroList
)

data class SuperHeroList(
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<SuperHero>
)

data class SuperHeroThumbnail(
    val path: String,
    val extension: String
)