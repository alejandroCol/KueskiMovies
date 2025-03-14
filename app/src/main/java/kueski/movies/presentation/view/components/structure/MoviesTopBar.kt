package kueski.movies.presentation.view.components.structure

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesTopBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onClearClick: () -> Unit,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    TopAppBar(
        title = {
            SearchBar(
                searchQuery = searchQuery,
                onQueryChange = onQueryChange,
                onClearClick = onClearClick
            )
        },
        actions = {
            LanguageDropdownMenu(
                selectedLanguage = selectedLanguage,
                onLanguageSelected = onLanguageSelected
            )
        }
    )
}