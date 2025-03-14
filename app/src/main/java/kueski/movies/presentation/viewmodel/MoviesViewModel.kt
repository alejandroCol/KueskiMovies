package kueski.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieQuery
import kueski.movies.domain.usecases.GetPopularMoviesUseCase
import kueski.movies.domain.usecases.SearchMoviesUseCase
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

   // private val _moviesState = MutableStateFlow<MoviesState>(MoviesState.Loading)
   // val moviesState: StateFlow<MoviesState> = _moviesState

    private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movies: StateFlow<PagingData<Movie>> = _movies

    private val _movieQuery = MutableStateFlow(MovieQuery(page = 1, language = "en"))
    val movieQuery: StateFlow<MovieQuery> = _movieQuery

    fun setLanguage(language: String) {
        _movieQuery.value = _movieQuery.value.copy(language = language)
        loadMovies()
    }

    fun onSearchMovies(query: String) {
        query.takeIf { it.isNotBlank() }?.let {
            applySearchMovies(it)
        } ?: loadMovies()
    }

    private fun applySearchMovies(query: String) {
        viewModelScope.launch {
            searchMoviesUseCase(query)
                .cachedIn(viewModelScope)
                .collect { _movies.value = it }
        }
    }
    private fun loadMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase(movieQuery.value)
                .cachedIn(viewModelScope)
                .collect { _movies.value = it }
        }
    }
}