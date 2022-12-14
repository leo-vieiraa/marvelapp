package com.example.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.model.DataResponse
import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.repository.SuperHeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelHomeListing @Inject constructor(
    private val repository: SuperHeroRepository
) : ViewModel() {

    private val _superHeroList = MutableLiveData<List<SuperHero>>()
    val superHeroList: LiveData<List<SuperHero>> = _superHeroList

    private val _featuredSuperHeroList = MutableLiveData<List<SuperHero>>()
    val featuredSuperHeroList: LiveData<List<SuperHero>> = _featuredSuperHeroList

    private val _page = MutableLiveData<Int>()
    val page: LiveData<Int> = _page

    private val _query = MutableLiveData<String?>()
    val query: LiveData<String?> = _query

    private val featuredHeroes = arrayListOf(
        "Black Widow",
        "Black Panther",
        "Iron Man",
        "Thor",
        "Hulk"
    )

    fun fetchSuperHeroes(page: Int = 0, limit: Int? = null) {
        viewModelScope.launch {
            _superHeroList.value = repository.fetchSuperHeroes(_query.value, limit, page)
        }
    }

    fun fetchFeaturedSuperHeroes() {

        for (item in featuredHeroes) {
            viewModelScope.launch {
                _featuredSuperHeroList.value = repository.fetchSuperHeroes(item, 1, 0)
            }
        }

    }

    fun fetchSuperHeroesFromDB() {
        viewModelScope.launch {
            _superHeroList.value =
                if (!_query.value.isNullOrBlank()) repository.fetchFromDB(_query.value)
                else repository.fetchFromDB()
        }
    }

    fun fetchFeaturedSuperHeroesFromDB() {
        for (item in featuredHeroes) {
            viewModelScope.launch {
                _featuredSuperHeroList.value = repository.fetchFromDB(item, 1)
            }
        }
    }

    fun nextPage() {
        _page.value = (_page.value ?: 0) + 20
    }

    fun searchFor(q: String?) {
        _query.value = q
        _page.value = 0
    }

}