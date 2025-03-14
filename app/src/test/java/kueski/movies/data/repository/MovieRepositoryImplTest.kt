package kueski.movies.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kueski.movies.data.local.database.MovieDao
import kueski.movies.data.remote.sources.MovieRemoteDataSource
import kueski.movies.domain.models.MovieDetail
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {

    private lateinit var movieRepositoryImpl: MovieRepositoryImpl
    private val remoteDataSource: MovieRemoteDataSource = mockk()
    private val localDataSource: MovieDao = mockk()

    @Before
    fun setUp() {
        movieRepositoryImpl = MovieRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `when getMovieDetail then return MovieDetail`() = runTest {
        val movieId = 123
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

        coEvery { remoteDataSource.fetchMovieDetail(movieId) } returns movieDetail

        val result = movieRepositoryImpl.getMovieDetail(movieId)

        assertEquals(movieDetail, result)
    }
}