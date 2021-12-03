package com.magnalleexample.geekbrains_movieviewer.ui.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.magnalleexample.geekbrains_movieviewer.R
import com.magnalleexample.geekbrains_movieviewer.app
import com.magnalleexample.geekbrains_movieviewer.databinding.MovieFragmentBinding
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import java.text.SimpleDateFormat

class MovieFragment : Fragment() {
    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: MovieFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.movie_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        val arguments = MovieFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        viewModel.repo = application.app.repository
        viewModel.movieData = arguments.movieData
        viewModel.synchMovieData()
        fillMovieData(viewModel.movieData, binding)

        return binding.root
    }

    private fun fillMovieData(movieData: MovieData, binding: MovieFragmentBinding) {
        binding.movieData = movieData
        binding.movieTitleTextView.text = movieData.name
        binding.movieImdbTextView.text = "TODO"
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        binding.movieReleaseDateTextView.text = simpleDateFormat.format(movieData.releaseDate)
        binding.movieProductionCompaniesTextView.text = "TODO"
        binding.movieProductionCountriesTextView.text = "TODO"
        binding.movieGenresTextView.text = movieData.genresToText()
        binding.movieKeywordsTextView.text = "TODO"
        binding.movieOverviewTextView.text = movieData.overview

        val IMAGE_URL = "https://image.tmdb.org/t/p/original${movieData.imageURL}?api_key=${Repo.API_KEY}"
        Glide
            .with(binding.root)
            .load(IMAGE_URL)
            .fitCenter()
            .placeholder(CircularProgressDrawable(binding.root.context).let {
                it.start()
                it})
            .into(binding.moviePosterImageView)
        binding.addRemoveToFavoritesToggleBtn.let{
            it.setOnClickListener {
                viewModel.setMovieInFavorites(binding.addRemoveToFavoritesToggleBtn.isChecked)
            }
            it.isChecked = movieData.inFavorites
        }
        binding.addRemoveToWatchListToggleBtn.let {
            it.setOnClickListener {
                viewModel.setMovieInWatchList(binding.addRemoveToWatchListToggleBtn.isChecked)
            }
            it.isChecked = movieData.inWatchList
        }
    }


}