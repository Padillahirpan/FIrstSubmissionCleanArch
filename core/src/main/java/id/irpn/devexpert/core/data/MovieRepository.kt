package id.irpn.devexpert.core.data

import android.util.Log
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

        val result: Flow<Resource<List<DataMovie>>> = flow {
            emit(Resource.Loading())
            when(val resultApi = remoteDataSource.getMovies().first()) {
                is ApiResponse.Success -> {
                    val listResponse = resultApi.data
                    val mapping = mappingMovieResponseToListDataMovie(listResponse)
                    emit(Resource.Success(mapping))
                }
                is ApiResponse.Empty -> {
                    val empty = mutableListOf<DataMovie>()
                    emit(Resource.Success(empty.toList()))
                }
                is ApiResponse.Error -> {
                    val error: Resource<List<DataMovie>> = Resource.Error(resultApi.errorMessage)
                    emit(error)
                }
            }
        }

        return result
    }

    override fun getDetailMovies(id: String): Flow<Resource<DataMovie>> {
        val result: Flow<Resource<DataMovie>> = flow {
            emit(Resource.Loading())
            when(val resultApi = remoteDataSource.getDetailMovie(id).first()) {
                is ApiResponse.Success -> {
                    val mapping = mappingMovieResponseToDataMovie(resultApi.data)
                    emit(Resource.Success(mapping))
                }
                is ApiResponse.Error -> {
                    val error: Resource<DataMovie> = Resource.Error(resultApi.errorMessage)
                    emit(error)
                }
            }
        }

        return result
    }

    override fun getListFavorite(): Flow<List<DataMovie>> {
        return localDataSource.getFavorites().map {
            mappingListMovieEntityToListDataMovie(it)
        }
//        val result: Flow<Resource<List<DataMovie>>> = flow {
//            emit(Resource.Loading())
//            try {
//                val listMovie = localDataSource.getFavorites().first()
//                val donp = mappingListMovieEntityToListDataMovie(listMovie)
//                emit(Resource.Success(donp))
//            } catch (e: Exception) {
//                val error: Resource<List<DataMovie>> = Resource.Error(e.message ?: "-")
//                emit(error)
//            }
//        }
//
//        return result
    }

    override fun getDetailFavorite(id: String): Flow<DataMovie?> {
        val result: Flow<DataMovie?> = flow {
            try {
                val doto = localDataSource.getDetailFav(id).first()
                Log.d("xyz","this is localDetail: $doto")
                emit(mappingMovieEntityToDataMovie(doto))
            } catch (e: Exception) {
                Log.d("xyz","this is localDetail ERROR: ${e.message}")
                emit(null)
            }
        }
        return result
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

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource
        ): MovieRepository {
            Log.d("xyz", "this is movieRepository: $instance")
            return instance ?: synchronized(this) {
                instance
                    ?: MovieRepository(remoteData, localData)
            }
        }
    }
}