package com.example.asteroidradarapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.asteroidradarapp.database.getDatabase
import com.example.asteroidradarapp.repository.AsteroidDataRepository
import kotlinx.coroutines.launch

class AsteroidListViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val asteroidDataRepository = AsteroidDataRepository(database)

    init {
        getAsteroidsListAndNasaImageProperties()
    }

    fun getAsteroidsListAndNasaImageProperties() {
        viewModelScope.launch {
            try {
                asteroidDataRepository.getNasaImageProperties()
                asteroidDataRepository.getAsteroidsList()
            } catch (e: Exception) {

            }
        }
    }

    val asteroidData = asteroidDataRepository.asteroidData

    val property = asteroidDataRepository.nasaImageData

}