package id.irpn.devexpert.core.data.source.remote

import id.irpn.devexpert.core.data.source.remote.network.ApiResponse
import id.irpn.devexpert.core.data.source.remote.network.ApiService
import id.irpn.devexpert.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class RemoteDataSource(
    private val apiService: ApiService
) {

    fun getMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlayingMovies()
                val listMovie = response.result
                if (listMovie != null) {
                    if (listMovie.isNotEmpty()) {
                        emit(ApiResponse.Success(listMovie))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovie(id: String): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId = id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    }
}