package kueski.movies.domain.interactor

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieQuery

interface MovieInteractor {
    fun getMovies(query: MovieQuery): Flow<PagingData<Movie>>
}