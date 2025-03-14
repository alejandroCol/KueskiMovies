package kueski.movies.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieQuery
import kueski.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieQuery: MovieQuery): Flow<PagingData<Movie>> {
        return repository.getPopularMovies(movieQuery)
    }
}