package kueski.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kueski.movies.domain.interactor.MovieInteractor
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieQuery
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor
) : ViewModel() {

    private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movies: StateFlow<PagingData<Movie>> = _movies

    private val _movieQuery = MutableStateFlow(MovieQuery(page = 1, language = "en", query = ""))
    val movieQuery: StateFlow<MovieQuery> = _movieQuery

    init {
        fetchMovies()
    }

    fun setLanguage(language: String) {
        _movieQuery.value = _movieQuery.value.copy(language = language)
        fetchMovies()
    }

    fun onSearchMovies(query: String) {
        _movieQuery.value = _movieQuery.value.copy(query = query)
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieInteractor.getMovies(movieQuery.value)
                .cachedIn(viewModelScope)
                .collect { _movies.value = it }
        }
    }
}