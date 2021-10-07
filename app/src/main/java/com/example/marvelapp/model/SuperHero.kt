package com.example.marvelapp.model

data class SuperHero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: SuperHeroThumbnail,
    val resourceURI: String,
    val comics: SuperHeroComics,
    val stories: SuperHeroSeries
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

data class SuperHeroComics(
    val available: Int,
    val collectionURI: String,
    val items: SuperHeroComicsItems
)

data class SuperHeroComicsItems(
    val resourceURI: String,
    val name: String
)

data class SuperHeroSeries(
    val available: Int,
    val collectionURI: String,
    val items: SuperHeroSeriesItems
)

data class SuperHeroSeriesItems(
    val resourceURI: String,
    val name: String
)