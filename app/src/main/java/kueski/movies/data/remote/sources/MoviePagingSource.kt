package kueski.movies.data.remote.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kueski.movies.data.remote.models.MovieQueryDto
import kueski.movies.domain.models.Movie

class MoviePagingSource(
    private val remoteDataSource: MovieRemoteDataSource,
    private val movieQuery: MovieQueryDto
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            movieQuery.page = params.key ?: 1
            val movies = remoteDataSource.fetchPopularMovies(movieQuery)

            LoadResult.Page(
                data = movies,
                prevKey = if (movieQuery.page == 1) null else movieQuery.page - 1,
                nextKey = if (movies.isEmpty()) null else movieQuery.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}