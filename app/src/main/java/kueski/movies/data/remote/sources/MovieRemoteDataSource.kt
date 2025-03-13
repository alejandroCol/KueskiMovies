package kueski.movies.data.remote.sources

import kueski.movies.data.remote.models.MovieQueryDto
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieDetail

interface MovieRemoteDataSource {
    suspend fun fetchPopularMovies(movieQuery: MovieQueryDto): List<Movie>
    suspend fun fetchMovieDetail(movieId: Int): MovieDetail
}