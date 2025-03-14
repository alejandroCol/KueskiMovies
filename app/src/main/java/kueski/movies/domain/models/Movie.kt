package kueski.movies.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Int,
    val rating: Double
)