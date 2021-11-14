package com.magnalleexample.geekbrains_movieviewer.ui.home

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.magnalleexample.geekbrains_movieviewer.R
import com.magnalleexample.geekbrains_movieviewer.databinding.HomeFragmentBinding
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieDataListener
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieListAdapter


class HomeFragment : Fragment() , HomeInterface.View{

    private lateinit var presenter : HomeInterface.Presenter
    override var watchListAdapter: MovieListAdapter? = null
    override var favoritesListAdapter: MovieListAdapter? = null

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val binding: HomeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false
        )

        watchListAdapter = MovieListAdapter(MovieDataListener{
            navigateToMovieData(it)
        })

        favoritesListAdapter = MovieListAdapter(MovieDataListener{
            navigateToMovieData(it)
        })

        binding.watchListRecyclerView.adapter = watchListAdapter
        binding.watchListRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.favoritesRecyclerView.adapter = favoritesListAdapter
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        presenter = HomePresenter()
        presenter.attach(this)
        val spinnerAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(application.applicationContext, android.R.layout.simple_spinner_item,
                presenter.getGenresListFormatted())
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genresSpinner.adapter = spinnerAdapter

        return binding.root
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun navigateToMovieData(movieData: MovieData){
        this.findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieFragment(movieData))
    }

    override fun updateWatchList() {
        watchListAdapter?.submitList(presenter.watchList)
    }

    override fun updateFavoritesList() {
        favoritesListAdapter?.submitList(presenter.favorites)
    }

}