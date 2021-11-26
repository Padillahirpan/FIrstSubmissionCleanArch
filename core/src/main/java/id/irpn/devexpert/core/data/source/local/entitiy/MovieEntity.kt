package id.irpn.devexpert.core.data.source.local.entitiy

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */


@Entity(tableName = "movie")
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    val id: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "desc")
    var desc: String,
    @ColumnInfo(name = "poster")
    var poster: String?,
    @ColumnInfo(name = "img_preview")
    var imagePreview: String?
)