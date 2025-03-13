package kueski.movies.domain.models

data class MovieDetail(
    val id: Int?,
    val title: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val overview: String,
    val releaseDate: String?,
    val runtime: Int?,
    val rating: Double?,
    val budget: Int?,
    val revenue: Int?,
    val tagline: String?
)
