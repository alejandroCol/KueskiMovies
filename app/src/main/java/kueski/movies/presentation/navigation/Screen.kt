package kueski.movies.presentation.navigation

sealed class Screen(val route: String) {
    data object MoviesList : Screen("movies_list")
    data object MovieDetailScreen : Screen("movie_detail_screen/{movieId}") {
        fun createRoute(movieId: Int) = "movie_detail_screen/$movieId"
    }
}