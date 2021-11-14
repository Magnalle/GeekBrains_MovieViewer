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
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieDataListener
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieListAdapter


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
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
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val spinnerAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(application.applicationContext, android.R.layout.simple_spinner_item,
                listOf("All genres").plus(Repo.genres.map { it.name }))
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genresSpinner.setAdapter(spinnerAdapter)

//        binding.genresSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                val item = parent.getItemAtPosition(position) as String
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        })

        val watchListAdapter = MovieListAdapter(MovieDataListener{
            viewModel.onMovieClicked(it)
        })
        binding.watchListRecyclerView.adapter = watchListAdapter
        binding.watchListRecyclerView.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.watchList.observe(viewLifecycleOwner, Observer {
            it?.let{
                watchListAdapter.submitList(it)
            }
        })

        val favoritesAdapter = MovieListAdapter(MovieDataListener{
            viewModel.onMovieClicked(it)
        })
        binding.favoritesRecyclerView.adapter = favoritesAdapter
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        viewModel.favorites.observe(viewLifecycleOwner, Observer {
            it?.let{
                favoritesAdapter.submitList(it)
            }
        })

        viewModel.navigateToMovieData.observe(viewLifecycleOwner, Observer { movieData ->
            movieData?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieFragment(movieData))
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

}