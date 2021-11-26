package id.irpn.devexpert.core.data.source.local.room

import androidx.room.*
import id.irpn.devexpert.core.data.source.local.entitiy.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getListFavorites(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where movie_id =:id ")
    fun getDetailFavorites(id: String): Flow<MovieEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Delete
    suspend fun deleteMovies(movie: MovieEntity)
}