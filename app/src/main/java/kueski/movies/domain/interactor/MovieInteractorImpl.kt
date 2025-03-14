package kueski.movies.domain.interactor

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieQuery
import kueski.movies.domain.usecases.GetPopularMoviesUseCase
import kueski.movies.domain.usecases.SearchMoviesUseCase
import javax.inject.Inject

class MovieInteractorImpl @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : MovieInteractor {
    override fun getMovies(query: MovieQuery): Flow<PagingData<Movie>> {
        return if (query.query.isNotBlank()) {
            searchMoviesUseCase(query)
        } else {
            getPopularMoviesUseCase(query)
        }
    }
}