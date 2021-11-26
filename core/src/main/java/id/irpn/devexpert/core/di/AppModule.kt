package id.irpn.devexpert.core.di

import id.irpn.devexpert.core.domain.usecase.MovieInteractor
import id.irpn.devexpert.core.domain.usecase.MovieUseCase
import org.koin.dsl.module

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}