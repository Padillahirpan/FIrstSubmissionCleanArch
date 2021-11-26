package id.irpn.devexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

@Parcelize
data class DataMovie(
    var id: String? = null,
    var name: String? = null,
    var desc: String? = null,
    var poster: String? = null,
    var img_preview: String? = null
): Parcelable