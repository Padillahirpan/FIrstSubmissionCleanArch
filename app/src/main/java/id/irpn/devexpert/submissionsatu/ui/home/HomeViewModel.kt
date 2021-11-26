package id.irpn.devexpert.submissionsatu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.irpn.devexpert.core.domain.usecase.MovieUseCase

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class HomeViewModel(
    movieUseCase: MovieUseCase
): ViewModel() {

    val movies = movieUseCase.getMovies().asLiveData()
}