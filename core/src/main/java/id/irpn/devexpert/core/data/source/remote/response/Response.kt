package id.irpn.devexpert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

data class ListResponse<T>(
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("results")
    val result: List<T>? = null
)