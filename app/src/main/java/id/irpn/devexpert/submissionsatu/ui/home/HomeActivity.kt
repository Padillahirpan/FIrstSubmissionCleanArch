package id.irpn.devexpert.submissionsatu.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.irpn.devexpert.core.data.Resource
import id.irpn.devexpert.core.domain.model.DataMovie
import id.irpn.devexpert.core.ui.MovieAdapter
import id.irpn.devexpert.submissionsatu.R
import id.irpn.devexpert.submissionsatu.databinding.ActivityMainBinding
import id.irpn.devexpert.submissionsatu.ui.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity: AppCompatActivity(), MovieAdapter.MovieAdapterCallback {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        viewModel.movies.observe(this) { movies ->
            if (movies != null) {
                when(movies) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setMovies(movies = movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = movies.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        movieAdapter = MovieAdapter(this@HomeActivity)
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
    }

    override fun onItemMovieClicked(movie: DataMovie) {
        startActivity(
            Intent(this, DetailMovieActivity::class.java)
                .putExtra(DetailMovieActivity.EXTRA_DATA, movie.id)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_favorite -> {
                try {
                    startActivity(Intent(this, Class.forName("id.irpn.devexpert.favorite.ui.favorite.FavoriteActivity")))
                } catch (e: Exception) {
                    Toast.makeText(this, "Module not found", Toast.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}