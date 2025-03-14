package kueski.movies.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kueski.movies.data.local.database.MovieDao
import kueski.movies.data.mappers.MovieMapper.toDto
import kueski.movies.data.remote.sources.MoviePagingSource
import kueski.movies.data.remote.sources.MovieRemoteDataSource
import kueski.movies.data.remote.sources.MovieSearchPagingSource
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieDetail
import kueski.movies.domain.models.MovieQuery
import kueski.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieDao
) : MovieRepository {

    override fun getPopularMovies(query: MovieQuery): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(remoteDataSource, localDataSource, query.toDto()) }
        ).flow
    }

    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MovieSearchPagingSource(localDataSource, query) }
        ).flow
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return remoteDataSource.fetchMovieDetail(movieId)
    }
}