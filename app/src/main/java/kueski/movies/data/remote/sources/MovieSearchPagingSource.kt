package kueski.movies.data.remote.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kueski.movies.data.local.database.MovieDao
import kueski.movies.data.mappers.MovieMapper.toDomain
import kueski.movies.domain.models.Movie

class MovieSearchPagingSource(
    private val localDataSource: MovieDao,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val movies = localDataSource.searchMovies(query).map { it.toDomain() }
            LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
}