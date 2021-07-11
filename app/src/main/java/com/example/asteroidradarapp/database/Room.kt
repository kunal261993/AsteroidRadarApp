package com.example.asteroidradarapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDataDao {
    @Query("select * from DatabaseAsteroidData where closeApproachDate BETWEEN :startDate AND :endDate ORDER BY closeApproachDate ASC")
    fun getVideos(startDate: String, endDate: String): LiveData<List<DatabaseAsteroidData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseAsteroidData)

    @Query("DELETE FROM DatabaseAsteroidData where closeApproachDate < :startDate")
    fun delete(startDate: String)
}

@Dao
interface NasaImageDao {
    @Query("select * from DatabaseNasaImageData ORDER BY date DESC")
    fun getVideos(): LiveData<DatabaseNasaImageData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: DatabaseNasaImageData)
}

@Database(entities = [DatabaseAsteroidData::class, DatabaseNasaImageData::class], version = 1)
abstract class AsteroidDataDatabase : RoomDatabase() {
    abstract val asteroidDataDao: AsteroidDataDao
    abstract val nasaImageDataDao: NasaImageDao
}

private lateinit var INSTANCE: AsteroidDataDatabase

fun getDatabase(context: Context): AsteroidDataDatabase {
    synchronized(AsteroidDataDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AsteroidDataDatabase::class.java,
                "AsteroidData"
            ).build()
        }
    }
    return INSTANCE
}
