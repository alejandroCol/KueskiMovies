package kueski.movies.data.remote.models

import com.google.gson.annotations.SerializedName

data class MovieDetailDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("vote_average") val rating: Double?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("revenue") val revenue: Int?,
    @SerializedName("tagline") val tagline: String?
)