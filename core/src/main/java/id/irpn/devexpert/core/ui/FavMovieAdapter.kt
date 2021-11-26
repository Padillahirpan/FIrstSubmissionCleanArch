package id.irpn.devexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.irpn.devexpert.core.databinding.ItemFavMovieBinding
import id.irpn.devexpert.core.domain.model.DataMovie

import id.irpn.devexpert.core.utils.Helper.API_IMAGE_ENDPOINT
import id.irpn.devexpert.core.utils.Helper.ENDPOINT_POSTER_SIZE_W500
import id.irpn.devexpert.core.utils.Helper.setImageWithGlide

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class FavMovieAdapter(
    private val movieAdapterCallback: FavMovieAdapterCallback
): RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder>() {

    private var listMovie = ArrayList<DataMovie>()

    fun setMovies(movies: List<DataMovie>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val binding = ItemFavMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val item = listMovie[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class FavMovieViewHolder(private val binding: ItemFavMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DataMovie) {
            with(binding) {
                tvTitle.text = movie.name
                root.setOnClickListener {
                    movieAdapterCallback.onItemFavMovieClicked(movie)
                }

                movie.poster?.let {
                    setImageWithGlide(
                        itemView.context,
                        API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W500 + it,
                        ivPoster
                    )
                }
            }
        }
    }

    interface FavMovieAdapterCallback {
        fun onItemFavMovieClicked(movie: DataMovie)
    }
}