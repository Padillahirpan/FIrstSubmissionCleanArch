package id.irpn.devexpert.core.domain.repository

import id.irpn.devexpert.core.data.Resource
import id.irpn.devexpert.core.domain.model.DataMovie
import kotlinx.coroutines.flow.Flow

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

interface IMovieRepository {
    fun getMovies(): Flow<Resource<List<DataMovie>>>
    fun getDetailMovies(id: String): Flow<Resource<DataMovie>>
    fun getListFavorite(): Flow<List<DataMovie>>
    fun getDetailFavorite(id: String): Flow<DataMovie?>
    fun setFavoriteMovie(movie: DataMovie, state: Boolean)
}