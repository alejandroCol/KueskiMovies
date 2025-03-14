package kueski.movies.presentation.viewmodel

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kueski.movies.domain.models.MovieDetail
import kueski.movies.domain.usecases.GetMovieDetailUseCase
import kueski.movies.presentation.state.MovieDetailState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private val getMovieDetailUseCase: GetMovieDetailUseCase = mockk()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        viewModel = MovieDetailViewModel(getMovieDetailUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when loadMovieDetail then should update movieDetail to Success`() = runTest {
        val movieId = 1
        val movieDetail = MovieDetail(
            id = movieId,
            title = "Avengers",
            overview = "Overview",
            posterUrl = null,
            backdropUrl = null,
            releaseDate = null,
            revenue = null,
            runtime = null,
            rating = null,
            budget = null,
            tagline = null
        )
        coEvery { getMovieDetailUseCase(movieId) } returns Result.success(movieDetail)

        viewModel.movieDetail.test {
            assertEquals(MovieDetailState.Loading, awaitItem())

            viewModel.loadMovieDetail(movieId)

            assertEquals(MovieDetailState.Success(movieDetail), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when loadMovieDetail then should update movieDetail to Error `() = runTest {
        val movieId = 1
        val errorMessage = "Error fetching movie detail"
        coEvery { getMovieDetailUseCase(movieId) } returns Result.failure(Exception(errorMessage))

        viewModel.movieDetail.test {
            assertEquals(MovieDetailState.Loading, awaitItem())

            viewModel.loadMovieDetail(movieId)

            assertEquals(MovieDetailState.Error(errorMessage), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
