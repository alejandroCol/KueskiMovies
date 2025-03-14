package kueski.movies.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieDetail
import kueski.movies.domain.models.MovieQuery

interface MovieRepository {
    fun getPopularMovies(query: MovieQuery): Flow<PagingData<Movie>>
    fun searchMovies(query: String): Flow<PagingData<Movie>>
    suspend fun getMovieDetail(movieId: Int): MovieDetail
}