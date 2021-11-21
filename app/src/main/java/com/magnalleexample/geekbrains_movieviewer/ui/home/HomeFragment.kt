package com.magnalleexample.geekbrains_movieviewer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.magnalleexample.geekbrains_movieviewer.R
import com.magnalleexample.geekbrains_movieviewer.app
import com.magnalleexample.geekbrains_movieviewer.databinding.HomeFragmentBinding
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieDataListener
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieListAdapter


class HomeFragment : Fragment() , HomeInterface.View {

    private lateinit var viewModel: HomeViewModel
    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val binding : HomeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false
        )
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.repo = application.app.repository
        viewModel.loadData()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val spinnerAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(application.applicationContext, android.R.layout.simple_spinner_item,
                viewModel.getGenresFormatted())
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genresSpinner.adapter = spinnerAdapter

        binding.genresSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // TODO
                val item = viewModel.getGenresFormatted().get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        viewModel.genresList.observe(viewLifecycleOwner, Observer {value->
            value?.let{ value ->
                val genreList = viewModel.getGenresFormatted()
                spinnerAdapter.clear()
                genreList.forEach{
                    spinnerAdapter.add(it)
                }
            }
        })


        val watchListAdapter = MovieListAdapter(MovieDataListener{
            viewModel.onMovieClicked(it)
        })
        binding.watchListRecyclerView.adapter = watchListAdapter
        binding.watchListRecyclerView.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.watchList?.observe(viewLifecycleOwner, Observer {
            it?.let{
                watchListAdapter.submitList(it)
            }
        })

        val favoritesAdapter = MovieListAdapter(MovieDataListener{
            viewModel.onMovieClicked(it)
        })
        binding.favoritesRecyclerView.adapter = favoritesAdapter
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        viewModel.favorites?.observe(viewLifecycleOwner, Observer {
            it?.let{
                favoritesAdapter.submitList(it)
            }
        })

        viewModel.navigateToMovieData.observe(viewLifecycleOwner, Observer { movieData ->
            navigateToMovieData(movieData)
        })

        return binding.root
    }

    override fun navigateToMovieData(movieData: MovieData?) {
        movieData?.let {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeToMovieDetails(movieData))
            viewModel.doneNavigating()
        }
    }
}