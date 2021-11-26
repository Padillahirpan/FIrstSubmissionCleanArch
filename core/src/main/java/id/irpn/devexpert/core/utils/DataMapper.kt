package id.irpn.devexpert.core.utils

import id.irpn.devexpert.core.data.source.local.entitiy.MovieEntity
import id.irpn.devexpert.core.data.source.remote.response.MovieResponse
import id.irpn.devexpert.core.domain.model.DataMovie

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

object DataMapper {

    fun mappingMovieResponseToListDataMovie(list: List<MovieResponse>): List<DataMovie> {
        return list.map {
            DataMovie(
                id = it.id.toString(),
                name = it.name,
                desc = it.desc,
                poster = it.poster,
                img_preview = it.img_preview
            )
        }
    }

    fun mappingListMovieEntityToListDataMovie(list: List<MovieEntity>): List<DataMovie> {
        return list.map {
            DataMovie(
                id = it.id,
                name = it.name,
                desc = it.desc,
                poster = it.poster,
                img_preview = it.imagePreview
            )
        }
    }

    fun mappingDataMovieToMovieEntity(dataMovie: DataMovie): MovieEntity {
        return MovieEntity(
            id = dataMovie.id ?: "-",
            name = dataMovie.name ?: "-",
            desc = dataMovie.desc ?: "-",
            poster = dataMovie.poster,
            imagePreview = dataMovie.img_preview
        )
    }

    fun mappingMovieResponseToDataMovie(movie: MovieResponse): DataMovie {
        return movie.let {
            DataMovie(
                id = it.id.toString(),
                name = it.name,
                desc = it.desc,
                poster = it.poster,
                img_preview = it.img_preview
            )
        }
    }

    fun mappingMovieEntityToDataMovie(movieEntity: MovieEntity?): DataMovie {
        return movieEntity?.let {
            DataMovie(
                id = it.id,
                name = it.name,
                desc = it.desc,
                poster = it.poster,
                img_preview = it.imagePreview
            )
        }?: DataMovie()
    }
}