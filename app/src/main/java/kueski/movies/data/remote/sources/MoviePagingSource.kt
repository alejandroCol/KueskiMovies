package kueski.movies.data.remote.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kueski.movies.data.local.database.MovieDao
import kueski.movies.data.mappers.MovieMapper.toDomain
import kueski.movies.data.mappers.MovieMapper.toEntity
import kueski.movies.data.remote.models.MovieQueryDto
import kueski.movies.domain.models.Movie

class MoviePagingSource(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieDao,
    private val movieQuery: MovieQueryDto
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        movieQuery.page = page

        return runCatching {
            val movies = remoteDataSource.fetchPopularMovies(movieQuery)
            localDataSource.insertMovies(movies.map { it.toEntity() })
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        }.getOrElse {
            val cachedMovies = localDataSource.getMovies().map { it.toDomain() }
            if (cachedMovies.isNotEmpty()) {
                LoadResult.Page(data = cachedMovies, prevKey = null, nextKey = null)
            } else {
                LoadResult.Error(it)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}