package id.irpn.devexpert.favorite.di

import id.irpn.devexpert.core.viewmodel.DetailFavViewModel
import id.irpn.devexpert.core.viewmodel.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */


object FavDataModule {
    val viewModelModule = module {
        viewModel { FavoriteViewModel(get()) }
        viewModel { DetailFavViewModel(get()) }
    }
}