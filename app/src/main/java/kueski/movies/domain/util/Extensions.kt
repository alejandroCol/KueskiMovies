package kueski.movies.domain.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.formatReleaseDate(): String {
    return try {
        val parsedDate = LocalDate.parse(this)
        val formatter = DateTimeFormatter.ofPattern("dd MM yyyy", Locale.getDefault())
        parsedDate.format(formatter)
    } catch (e: Exception) {
        "Unknown Date"
    }
}

fun Double.formatRating(): String {
    return BigDecimal(this).setScale(1, RoundingMode.HALF_UP).toString()
}