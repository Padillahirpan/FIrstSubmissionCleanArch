package id.irpn.devexpert.core.data.source.remote.network

import id.irpn.devexpert.core.BuildConfig
import id.irpn.devexpert.core.data.source.remote.response.ListResponse
import id.irpn.devexpert.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): ListResponse<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieResponse
}