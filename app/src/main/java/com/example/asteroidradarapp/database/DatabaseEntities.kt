package com.example.asteroidradarapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.asteroidradarapp.domain.AsteroidData
import com.example.asteroidradarapp.domain.NasaImageData

@Entity
data class DatabaseAsteroidData constructor(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

@Entity
data class DatabaseNasaImageData constructor(
    @PrimaryKey
    var date: String = "",
    var url: String = "",
    var mediaType: String = "",
    var title: String = ""
)


@JvmName("asDomainModelDatabaseAsteroidData")
fun List<DatabaseAsteroidData>.asDomainModel(): List<AsteroidData> {
    return map {
        AsteroidData(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

@JvmName("asDomainModelDatabaseNasaImageData")
fun DatabaseNasaImageData.asDomainModel(): NasaImageData {
    val nasaImageData = NasaImageData()
    nasaImageData.date = date
    nasaImageData.url = url
    nasaImageData.title = title
    nasaImageData.mediaType = mediaType
    return nasaImageData
}


