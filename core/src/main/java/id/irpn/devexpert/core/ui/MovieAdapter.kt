package id.irpn.devexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.irpn.devexpert.core.databinding.ItemMovieBinding
import id.irpn.devexpert.core.domain.model.DataMovie
import id.irpn.devexpert.core.utils.Helper.API_IMAGE_ENDPOINT
import id.irpn.devexpert.core.utils.Helper.ENDPOINT_POSTER_SIZE_W500
import id.irpn.devexpert.core.utils.Helper.setImageWithGlide

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class MovieAdapter(
    private val movieAdapterCallback: MovieAdapterCallback
): RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

    private var listMovie = ArrayList<DataMovie>()

    fun setMovies(movies: List<DataMovie>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = listMovie[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listMovie.size
    inner class MoviesViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DataMovie) {
            with(binding) {
                tvTitle.text = movie.name
                root.setOnClickListener {
                    movieAdapterCallback.onItemMovieClicked(movie)
                }

                movie.img_preview?.let {
                    setImageWithGlide(itemView.context, API_IMAGE_ENDPOINT+ ENDPOINT_POSTER_SIZE_W500 + it, ivPoster)
                }
            }
        }
    }

    interface MovieAdapterCallback {
        fun onItemMovieClicked(movie: DataMovie)
    }
}