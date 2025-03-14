package kueski.movies.domain.usecases

import kueski.movies.domain.models.MovieDetail
import kueski.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<MovieDetail> {
        return runCatching { repository.getMovieDetail(movieId) }
    }
}
