package id.irpn.devexpert.submissionsatu.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.irpn.devexpert.core.data.Resource
import id.irpn.devexpert.core.domain.model.DataMovie
import id.irpn.devexpert.core.domain.usecase.MovieUseCase

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class DetailMovieViewModel(
    val movieUseCase: MovieUseCase
): ViewModel() {
    var statusFavorite: Boolean = false

    fun getDetailMovie(id: String): LiveData<Resource<DataMovie>> {
        val data = movieUseCase.getDetailMovies(id).asLiveData()
        return data
    }

    fun getDetailLocal(id: String): LiveData<DataMovie?> {
        return movieUseCase.getDetailFavorite(id).asLiveData()
    }

    fun setFavoriteDataMovie(dataMovie: DataMovie, state: Boolean) {
        movieUseCase.setFavoriteMovie(dataMovie, state)
    }

}