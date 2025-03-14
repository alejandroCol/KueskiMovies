package kueski.movies.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun KueskiTypography(): Typography {
    val colors = LocalCustomColors.current

    return Typography(
        titleLarge = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colors.regularText
        ),
        bodyMedium = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = colors.regularText
        )
    )
}