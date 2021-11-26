package id.irpn.devexpert.core.data.source.local

import id.irpn.devexpert.core.data.source.local.entitiy.MovieEntity
import id.irpn.devexpert.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class LocalDataSource(
    val movieDao: MovieDao
) {

    fun getFavorites(): Flow<List<MovieEntity>> {
        return movieDao.getListFavorites()
    }

    fun getDetailFav(id: String) = movieDao.getDetailFavorites(id)

    suspend fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovies(movies)
    suspend fun deleteMovie(movie: MovieEntity) = movieDao.deleteMovies(movie)

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }

    }
}