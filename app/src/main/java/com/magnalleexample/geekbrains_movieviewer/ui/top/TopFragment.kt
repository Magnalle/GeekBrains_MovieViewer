package com.magnalleexample.geekbrains_movieviewer.ui.top

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridLayout.HORIZONTAL
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magnalleexample.geekbrains_movieviewer.R
import com.magnalleexample.geekbrains_movieviewer.app
import com.magnalleexample.geekbrains_movieviewer.databinding.HomeFragmentBinding
import com.magnalleexample.geekbrains_movieviewer.databinding.TopFragmentBinding
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.ui.home.HomeFragmentDirections
import com.magnalleexample.geekbrains_movieviewer.ui.home.HomeViewModel
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieDataListener
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieListAdapter

class TopFragment : Fragment() {

    companion object {
        fun newInstance() = TopFragment()
    }

    private lateinit var viewModel: TopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val binding : TopFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.top_fragment, container, false
        )
        viewModel = ViewModelProvider(this).get(TopViewModel::class.java)
        viewModel.sharedPreferences = application.app.sharedPreferences
        viewModel.repo = application.app.repository
        viewModel.loadData()
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

        val topListAdapter = MovieListAdapter(MovieDataListener{
            viewModel.onMovieClicked(it)
        })
        binding.topListRecyclerView.adapter = topListAdapter
        //binding.topListRecyclerView.layoutManager = GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false)
        binding.topListRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        viewModel.topList.observe(viewLifecycleOwner, Observer {
            it?.let{
                topListAdapter.submitList(it)
            }
        })
        viewModel.navigateToMovieData.observe(viewLifecycleOwner, Observer { movieData ->
            navigateToMovieData(movieData)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer{
            it?.let {
                viewModel.disableError()
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopViewModel::class.java)
    }

    fun navigateToMovieData(movieData: MovieData?) {
        movieData?.let {
            findNavController().navigate(
                TopFragmentDirections.actionNavigationTopToNavigationMovieDetails(movieData))
            viewModel.doneNavigating()
        }
    }
}