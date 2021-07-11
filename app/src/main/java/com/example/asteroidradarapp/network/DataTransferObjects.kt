package com.example.asteroidradarapp.network

import com.example.asteroidradarapp.database.DatabaseAsteroidData
import com.example.asteroidradarapp.database.DatabaseNasaImageData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkAsteroidDataContainer(val videos: List<NetworkAsteroidData>)

@JsonClass(generateAdapter = true)
data class NetworkAsteroidData(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

@JsonClass(generateAdapter = true)
data class NetworkNasaImageData(
    val date: String = "",
    val url: String = "",
    @Json(name = "media_type")
    val mediaType: String = "",
    val title: String = "",
)

fun NetworkNasaImageData.asDatabaseModel(): DatabaseNasaImageData {
    val databaseNasaImageData = DatabaseNasaImageData()
    databaseNasaImageData.date = date
    databaseNasaImageData.url = url
    databaseNasaImageData.title = title
    databaseNasaImageData.mediaType = mediaType
    return databaseNasaImageData
}

fun NetworkAsteroidDataContainer.asDatabaseModel(): Array<DatabaseAsteroidData> {
    return videos.map {
        DatabaseAsteroidData(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}
