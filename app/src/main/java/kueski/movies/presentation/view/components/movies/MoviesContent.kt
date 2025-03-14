package kueski.movies.presentation.view.components.movies

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import kueski.movies.domain.models.Movie
import kueski.movies.presentation.view.components.states.EmptyState
import kueski.movies.presentation.view.components.states.ErrorState
import kueski.movies.presentation.view.components.states.LoadingState

@Composable
fun MoviesContent(
    movies: LazyPagingItems<Movie>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    when (movies.loadState.refresh) {
        is LoadState.Loading -> LoadingState()
        is LoadState.Error -> {
            val errorState = movies.loadState.refresh as LoadState.Error
            ErrorState(errorState.error.localizedMessage)
        }
        is LoadState.NotLoading -> {
            if (movies.itemCount == 0) {
                EmptyState()
            } else {
                LazyColumn(modifier = modifier.padding(16.dp)) {
                    items(movies.itemCount) { index ->
                        movies[index]?.let { movie ->
                            MovieItem(movie = movie, navController = navController)
                        }
                    }
                }
            }
        }
    }
}