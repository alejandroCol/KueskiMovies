package kueski.movies.presentation.state

import kueski.movies.domain.models.MovieDetail

sealed class MovieDetailState {
    object Loading : MovieDetailState()
    data class Success(val movie: MovieDetail) : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
}