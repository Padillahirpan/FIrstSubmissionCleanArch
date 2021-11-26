package id.irpn.devexpert.core.data

import id.irpn.devexpert.core.data.source.local.LocalDataSource
import id.irpn.devexpert.core.data.source.remote.RemoteDataSource
import id.irpn.devexpert.core.data.source.remote.network.ApiResponse
import id.irpn.devexpert.core.domain.model.DataMovie
import id.irpn.devexpert.core.domain.repository.IMovieRepository
import id.irpn.devexpert.core.utils.DataMapper.mappingDataMovieToMovieEntity
import id.irpn.devexpert.core.utils.DataMapper.mappingListMovieEntityToListDataMovie
import id.irpn.devexpert.core.utils.DataMapper.mappingMovieEntityToDataMovie
import id.irpn.devexpert.core.utils.DataMapper.mappingMovieResponseToDataMovie
import id.irpn.devexpert.core.utils.DataMapper.mappingMovieResponseToListDataMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IMovieRepository {
    override fun getMovies(): Flow<Resource<List<DataMovie>>> {
        return flow {
            this.emit(Resource.Loading())
            when (val resultApi = remoteDataSource.getMovies().first()) {
                is ApiResponse.Success -> {
                    val listResponse = resultApi.data
                    val mapping = mappingMovieResponseToListDataMovie(listResponse)
                    this.emit(Resource.Success(mapping))
                }
                is ApiResponse.Empty -> {
                    val empty = mutableListOf<DataMovie>()
                    this.emit(Resource.Success(empty.toList()))
                }
                is ApiResponse.Error -> {
                    val error: Resource<List<DataMovie>> = Resource.Error(resultApi.errorMessage)
                    this.emit(error)
                }
            }
        }
    }

    override fun getDetailMovies(id: String): Flow<Resource<DataMovie>> {
        return flow {
            this.emit(Resource.Loading())
            when (val resultApi = remoteDataSource.getDetailMovie(id).first()) {
                is ApiResponse.Success -> {
                    val mapping = mappingMovieResponseToDataMovie(resultApi.data)
                    this.emit(Resource.Success(mapping))
                }
                is ApiResponse.Error -> {
                    val error: Resource<DataMovie> = Resource.Error(resultApi.errorMessage)
                    this.emit(error)
                }
            }
        }
    }

    override fun getListFavorite(): Flow<List<DataMovie>> {
        return localDataSource.getFavorites().map {
            mappingListMovieEntityToListDataMovie(it)
        }
    }

    override fun getDetailFavorite(id: String): Flow<DataMovie?> {
        return flow {
            try {
                val doto = localDataSource.getDetailFav(id).first()
                this.emit(mappingMovieEntityToDataMovie(doto))
            } catch (e: Exception) {
                this.emit(null)
            }
        }
    }

    override fun setFavoriteMovie(movie: DataMovie, state: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            movie.id?.let { id ->
                if (state) {
                    val dataDetailFav = localDataSource.getDetailFav(id).first()
                    if (dataDetailFav == null) {
                        val dataMovieEntity = mappingDataMovieToMovieEntity(movie)
                        localDataSource.insertMovie(listOf(dataMovieEntity))
                    }
                } else {
                    localDataSource.deleteMovie(movie = mappingDataMovieToMovieEntity(movie))
                }
            }
        }
    }
}