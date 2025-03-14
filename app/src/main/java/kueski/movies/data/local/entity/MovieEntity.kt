package kueski.movies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val posterUrl: String?,
    val overview: String?,
    val releaseDate: String?,
    val runtime: Int?,
    val rating: Double?
)