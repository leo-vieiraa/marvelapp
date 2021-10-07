package com.example.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.repository.SuperHeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SuperHeroRepository
) : ViewModel() {

    val _superHeroList = MutableLiveData<List<SuperHero>>()
    var superHeroList: LiveData<List<SuperHero>> = _superHeroList

    fun fetchSuperHeroes() {
        viewModelScope.launch {
            _superHeroList.value = repository.fetchSuperHeroes()
        }
    }

}