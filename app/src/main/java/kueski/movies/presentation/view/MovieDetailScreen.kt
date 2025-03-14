package kueski.movies.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kueski.movies.presentation.state.MovieDetailState
import kueski.movies.presentation.view.components.movies.MovieDetailsContent
import kueski.movies.presentation.view.components.states.ErrorState
import kueski.movies.presentation.viewmodel.MovieDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val movieDetailState by viewModel.movieDetail.collectAsState()
    val currentMovieId by rememberUpdatedState(movieId)

    LaunchedEffect(currentMovieId) {
        viewModel.loadMovieDetail(currentMovieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (movieDetailState) {
                is MovieDetailState.Loading -> CircularProgressIndicator()

                is MovieDetailState.Success -> {
                    val movie = (movieDetailState as MovieDetailState.Success).movie
                    MovieDetailsContent(movie = movie)
                }

                is MovieDetailState.Error -> ErrorState()
            }
        }
    }
}