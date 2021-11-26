package id.irpn.devexpert.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.irpn.devexpert.core.domain.usecase.MovieUseCase

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class FavoriteViewModel(
    movieUseCase: MovieUseCase
): ViewModel() {

    val movies = movieUseCase.getListFavorite().asLiveData()
}