package com.example.asteroidradarapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidradarapp.database.getDatabase
import com.example.asteroidradarapp.repository.AsteroidDataRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidDataRepository(database)
        return try {
            repository.getAsteroidsList()
            repository.getNasaImageProperties()
            repository.deleteAsteroids()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
