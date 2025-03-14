package kueski.movies.data.mappers

import kueski.movies.data.local.entity.MovieEntity
import kueski.movies.data.remote.models.MovieDetailDto
import kueski.movies.data.remote.models.MovieDto
import kueski.movies.data.remote.models.MovieQueryDto
import kueski.movies.domain.models.Movie
import kueski.movies.domain.models.MovieDetail
import kueski.movies.domain.models.MovieQuery

object MovieMapper {
    fun MovieDetailDto.toDomain() = MovieDetail(
        id = id,
        title = title ?: "",
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        backdropUrl = "https://image.tmdb.org/t/p/w500$backdropPath",
        overview = overview ?: "",
        releaseDate = releaseDate,
        runtime = runtime,
        rating = rating,
        budget = budget,
        revenue = revenue,
        tagline = tagline
    )

    fun MovieDto.toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            posterUrl = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
            overview = overview,
            releaseDate = releaseDate,
            runtime = 0,
            rating = rating
        )
    }

    fun MovieQuery.toDto(): MovieQueryDto {
        return MovieQueryDto(page = this.page, language = this.language)
    }

    fun MovieEntity.toDomain(): Movie {
        return Movie(
            id = id,
            title = title ?: "",
            posterUrl = posterUrl ?: "",
            overview = overview ?: "",
            releaseDate = releaseDate ?: "",
            runtime = 0,
            rating = rating ?: 0.0
        )
    }

    fun Movie.toEntity(): MovieEntity {
        return MovieEntity(
            id = this.id,
            title = this.title,
            posterUrl = this.posterUrl,
            overview = this.overview,
            releaseDate = this.releaseDate,
            runtime = 0,
            rating = this.rating
        )
    }
}