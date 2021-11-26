package id.irpn.devexpert.core.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

object Helper {
    const val API_IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/"
    const val ENDPOINT_POSTER_SIZE_W500 = "w500"
    const val ENDPOINT_POSTER_SIZE_W780 = "w780"

    fun setImageWithGlide(context: Context, imagePath: String, imageView: ImageView) {
        Glide.with(context).clear(imageView)
        Glide.with(context).load(imagePath).into(imageView)
    }
}