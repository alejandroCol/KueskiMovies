package kueski.movies.presentation.navigation

sealed class Screen(val route: String) {
    object MoviesList : Screen("movies_list")
    object MovieDetailScreen : Screen("movie_detail_screen/{movieId}") {
        fun createRoute(movieId: Int) = "movie_detail_screen/$movieId"
    }
}