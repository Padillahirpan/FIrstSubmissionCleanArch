package id.irpn.devexpert.favorite.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.irpn.devexpert.core.domain.model.DataMovie
import id.irpn.devexpert.core.ui.FavMovieAdapter
import id.irpn.devexpert.core.viewmodel.FavoriteViewModel
import id.irpn.devexpert.favorite.databinding.ActivityFavoriteBinding
import id.irpn.devexpert.favorite.ui.detail.DetailFavMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity(), FavMovieAdapter.FavMovieAdapterCallback {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    private lateinit var movieAdapter: FavMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        viewModel.movies.observe(this) { moviesLocal ->
            populateData(moviesLocal)
        }
    }

    private fun populateData(list: List<DataMovie>?) {
        movieAdapter.setMovies(movies = list)
    }

    private fun setupAdapter() {
        movieAdapter = FavMovieAdapter(this)
        binding.rvFavoMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
    }

    override fun onItemFavMovieClicked(movie: DataMovie) {
        startActivity(
            Intent(this, DetailFavMovieActivity::class.java)
                .putExtra(DetailFavMovieActivity.EXTRA_DATA, movie.id)
        )
    }
}