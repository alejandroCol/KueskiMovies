package kueski.movies.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kueski.movies.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    suspend fun searchMovies(query: String): List<MovieEntity>

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}