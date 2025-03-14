package kueski.movies.domain.models

data class MovieQuery(
    var page: Int = 1,
    val language: String = "en",
    val query: String = ""
)