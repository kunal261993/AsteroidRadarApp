package com.example.asteroidradarapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.asteroidradarapp.database.AsteroidDataDatabase
import com.example.asteroidradarapp.database.asDomainModel
import com.example.asteroidradarapp.domain.AsteroidData
import com.example.asteroidradarapp.domain.NasaImageData
import com.example.asteroidradarapp.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

class AsteroidDataRepository(private val database: AsteroidDataDatabase) {
    val date = NetworkUtils().getNextSevenDaysFormattedDates()

    val asteroidData: LiveData<List<AsteroidData>> =
        Transformations.map(database.asteroidDataDao.getVideos(date[0], date[7])) {
            it.asDomainModel()
        }

    val nasaImageData: LiveData<NasaImageData> =
        Transformations.map(database.nasaImageDataDao.getVideos()) {
            it?.let { it.asDomainModel() }
        }

    suspend fun getAsteroidsList() {
        withContext(Dispatchers.IO) {
            try {
                val playlist: Response<String> =
                    AsteroidApi.retrofitService.getAsteroidList(date[0], date[7]).execute()
                val networkAsteroidList =
                    NetworkUtils().parseAsteroidsJsonResult(JSONObject(playlist.body()))
                database.asteroidDataDao.insertAll(*NetworkAsteroidDataContainer(networkAsteroidList).asDatabaseModel())
            } catch (e: java.lang.Exception) {

            }
        }
    }

    suspend fun getNasaImageProperties() {
        withContext(Dispatchers.IO) {
            try {
                val result = AsteroidApi.retrofitService.getNasaImage()
                database.nasaImageDataDao.insertAll(result.asDatabaseModel())
            } catch (e: Exception) {

            }
        }

    }

    suspend fun deleteAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                database.asteroidDataDao.delete(date[0])
            } catch (e: Exception) {

            }
        }
    }
}
