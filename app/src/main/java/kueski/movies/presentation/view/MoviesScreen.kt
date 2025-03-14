package kueski.movies.presentation.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.paging.LoadState
import kotlinx.coroutines.delay
import kueski.movies.R
import kueski.movies.presentation.view.components.LanguageDropdownMenu
import kueski.movies.presentation.view.components.MovieItem
import kueski.movies.presentation.view.components.SearchBar
import kueski.movies.presentation.viewmodel.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(navController: NavController, viewModel: MoviesViewModel) {
    val movieQuery by viewModel.movieQuery.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val movies = viewModel.movies.collectAsLazyPagingItems()


    LaunchedEffect(searchQuery) {
        delay(500)
        viewModel.onSearchMovies(searchQuery)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchBar(
                        searchQuery = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onClearClick = { searchQuery = "" }
                    )
                },
                actions = {
                    LanguageDropdownMenu(
                        selectedLanguage = movieQuery.language,
                        onLanguageSelected = { viewModel.setLanguage(it) }
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (movies.loadState.refresh) {
                is LoadState.Loading -> CircularProgressIndicator()
                is LoadState.Error -> {
                    Text(
                        text = stringResource(id = R.string.error_message),
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    val errorMessage = (movies.loadState.refresh as LoadState.Error)
                        .error.localizedMessage
                    Log.e(errorMessage,"aqui seria una opcion de logs")

                }
                is LoadState.NotLoading -> {
                    if (movies.itemCount == 0) {
                        Text(
                            text = stringResource(id = R.string.empty_message),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        LazyColumn(modifier = Modifier.padding(16.dp)) {
                            items(movies.itemCount) { index ->
                                movies[index]?.let {
                                    MovieItem(movie = it, navController = navController) }
                            }
                        }
                    }
                }
            }
        }
    }
}
