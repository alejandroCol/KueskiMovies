package kueski.movies.data.remote.models

data class MovieQueryDto(
    var page: Int = 1,
    val language: String = "en"
)