package kueski.movies.data.remote.sources

import kueski.movies.data.mappers.MovieMapper.toDomain
import kueski.movies.data.remote.models.MovieQueryDto
import kueski.movies.data.remote.network.MovieApiService
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieDetail
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieApiService
) : MovieRemoteDataSource {
    override suspend fun fetchPopularMovies(movieQuery: MovieQueryDto): List<Movie> {
        return apiService.getPopularMovies(
            page = movieQuery.page,
            language = movieQuery.language
        ).results.map { it.toDomain() }
    }
    override suspend fun fetchMovieDetail(movieId: Int): MovieDetail {
        return apiService.getMovieDetail(movieId).toDomain()
    }
}
