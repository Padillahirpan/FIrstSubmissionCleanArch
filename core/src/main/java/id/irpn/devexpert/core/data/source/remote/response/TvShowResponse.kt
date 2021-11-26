package id.irpn.devexpert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

data class TvShowResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("overview")
    var desc: String? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("backdrop_path")
    var img_preview: String? = null
)