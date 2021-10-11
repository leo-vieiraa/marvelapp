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
class HomeListingViewModel @Inject constructor(
    private val repository: SuperHeroRepository
) : ViewModel() {

    private val _superHeroList = MutableLiveData<List<SuperHero>>()
    val superHeroList: LiveData<List<SuperHero>> = _superHeroList

    private val _page = MutableLiveData<Int>()
    val page: LiveData<Int> = _page

    private var _query: String? = null

    fun fetchSuperHeroes(page: Int = 1, limit: Int? = null, offset: Int? = null) {
        viewModelScope.launch {
            _superHeroList.value = repository.fetchSuperHeroes(_query, limit, offset)
        }
    }

    fun nextPage() {
        _page.value = (_page.value ?: 0) + 1
    }

    fun searchFor(q: String?) {
        _query = q
        _page.value = 1
    }

}