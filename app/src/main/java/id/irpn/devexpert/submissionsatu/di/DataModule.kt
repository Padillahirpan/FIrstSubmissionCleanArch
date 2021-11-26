package id.irpn.devexpert.submissionsatu.di

import id.irpn.devexpert.core.viewmodel.DetailFavViewModel
import id.irpn.devexpert.core.viewmodel.FavoriteViewModel
import id.irpn.devexpert.submissionsatu.ui.detail.DetailMovieViewModel
import id.irpn.devexpert.submissionsatu.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

object DataModule {
    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { DetailMovieViewModel(get()) }
        viewModel { FavoriteViewModel(get()) }
        viewModel { DetailFavViewModel(get()) }

    }
}