package kueski.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kueski.movies.domain.util.Constants.MOVIE_ID_ARG
import kueski.movies.presentation.navigation.Screen
import kueski.movies.presentation.theme.MovieAppTheme
import kueski.movies.presentation.view.MovieDetailScreen
import kueski.movies.presentation.view.MoviesScreen
import kueski.movies.presentation.viewmodel.MoviesViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MovieAppTheme {
                AppNavigation(navController)
            }
        }
    }

    @Composable
    fun AppNavigation(navController: NavHostController) {
        NavHost(navController = navController, startDestination = Screen.MoviesList.route) {
            composable(Screen.MoviesList.route) {
                val viewModel = hiltViewModel<MoviesViewModel>()
                MoviesScreen(navController, viewModel)
            }
            composable(
                route = Screen.MovieDetailScreen.route,
                arguments = listOf(navArgument(MOVIE_ID_ARG) { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt(MOVIE_ID_ARG) ?: return@composable
                MovieDetailScreen(movieId, navController)
            }
        }
    }
}