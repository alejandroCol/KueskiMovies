package kueski.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kueski.movies.data.mappers.MovieMapper.toDto
import kueski.movies.data.remote.sources.MoviePagingSource
import kueski.movies.data.remote.sources.MovieRemoteDataSource
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieDetail
import kueski.movies.domain.models.MovieQuery
import kueski.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override fun getPopularMovies(query: MovieQuery): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(remoteDataSource, query.toDto()) }
        ).flow
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return remoteDataSource.fetchMovieDetail(movieId)
    }
}