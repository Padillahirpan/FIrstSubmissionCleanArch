package id.irpn.devexpert.core.domain.usecase

import id.irpn.devexpert.core.data.Resource
import id.irpn.devexpert.core.domain.model.DataMovie
import id.irpn.devexpert.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class MovieInteractor(
    private val movieRepository: IMovieRepository
): MovieUseCase {
    override fun getMovies(): Flow<Resource<List<DataMovie>>> {
        return movieRepository.getMovies()
    }

    override fun getDetailMovies(id: String): Flow<Resource<DataMovie>> {
        return movieRepository.getDetailMovies(id)
    }

    override fun getListFavorite(): Flow<List<DataMovie>> {
        return movieRepository.getListFavorite()
    }

    override fun getDetailFavorite(id: String): Flow<DataMovie?> {
        return movieRepository.getDetailFavorite(id)
    }

    override fun setFavoriteMovie(movie: DataMovie, state: Boolean) {
        movieRepository.setFavoriteMovie(movie, state)
    }

}