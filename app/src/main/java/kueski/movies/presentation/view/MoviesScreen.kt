package kueski.movies.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.navigation.NavController
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kueski.movies.presentation.view.components.movies.MoviesContent
import kueski.movies.presentation.view.components.structure.MoviesTopBar
import kueski.movies.presentation.viewmodel.MoviesViewModel

@OptIn(FlowPreview::class)
@Composable
fun MoviesScreen(navController: NavController, viewModel: MoviesViewModel) {
    val movieQuery by viewModel.movieQuery.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val movies = viewModel.movies.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        snapshotFlow { searchQuery }
            .debounce(500)
            .collectLatest { query ->
                viewModel.onSearchMovies(query)
            }
    }

    Scaffold(
        topBar = {
            MoviesTopBar(
                searchQuery = searchQuery,
                onQueryChange = { searchQuery = it },
                onClearClick = { searchQuery = "" },
                selectedLanguage = movieQuery.language,
                onLanguageSelected = viewModel::setLanguage
            )
        }
    ) { innerPadding ->
        MoviesContent(
            movies = movies,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}