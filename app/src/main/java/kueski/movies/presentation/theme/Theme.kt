package kueski.movies.presentation.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFBB86FC),
    onPrimary = Color.Black,
    background = Color.Black,
    onBackground = Color.White
)

private val LightCustomColors = KueskiColors(regularText = Color(0xFF0A3B38))
private val DarkCustomColors = KueskiColors(regularText = Color(0xFFFFFFFF))

val LocalCustomColors = staticCompositionLocalOf { LightCustomColors }

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkCustomColors else LightCustomColors
    CompositionLocalProvider(LocalCustomColors provides colors) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColors else LightColors,
            typography = KueskiTypography(),
            content = content
        )
    }
}