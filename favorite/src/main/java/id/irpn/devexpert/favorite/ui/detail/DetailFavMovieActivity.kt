package id.irpn.devexpert.favorite.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.irpn.devexpert.core.domain.model.DataMovie
import id.irpn.devexpert.core.utils.Helper
import id.irpn.devexpert.core.viewmodel.DetailFavViewModel
import id.irpn.devexpert.submissionsatu.R
import id.irpn.devexpert.submissionsatu.databinding.ActivityDetailMovieBinding
import id.irpn.devexpert.submissionsatu.ui.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFavMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel: DetailFavViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(DetailMovieActivity.EXTRA_DATA, "-1")

            viewModel.getDetailLocal(id).observe(this) { dataMovie ->
                if (dataMovie?.id == null) {
                    setStatusFavorite(false)
                    populateData(null)
                } else {
                    binding.progressBar.visibility = View.GONE
                    viewModel.statusFavorite = true
                    setStatusFavorite(true)
                    populateData(dataMovie)
                }
            }
        }
    }

    private fun populateData(dataMovie: DataMovie?) {
        dataMovie?.let { movie ->
            binding.tvDetailTitleMovie.text = movie.name
            binding.tvDetailOverview.text = movie.desc

            Helper.setImageWithGlide(
                this,
                Helper.API_IMAGE_ENDPOINT + Helper.ENDPOINT_POSTER_SIZE_W780 + movie.img_preview,
                binding.ivDetailPoster
            )

            binding.fab.setOnClickListener {
                if (viewModel.statusFavorite) {
                    viewModel.setFavoriteDataMovie(movie, false)
                } else {
                    viewModel.setFavoriteDataMovie(movie, true)
                }
                viewModel.statusFavorite = !viewModel.statusFavorite
                setStatusFavorite(viewModel.statusFavorite)
            }
        } ?: showError(getString(R.string.no_data))
    }

    private fun showError(errorMessage: String?) {
        binding.progressBar.visibility = View.GONE
        binding.viewError.root.visibility = View.VISIBLE
        binding.viewError.tvError.text = errorMessage ?: getString(R.string.something_wrong)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}