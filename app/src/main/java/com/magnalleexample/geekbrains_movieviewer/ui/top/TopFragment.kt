package com.magnalleexample.geekbrains_movieviewer.ui.top

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magnalleexample.geekbrains_movieviewer.R
import com.magnalleexample.geekbrains_movieviewer.app
import com.magnalleexample.geekbrains_movieviewer.databinding.HomeFragmentBinding
import com.magnalleexample.geekbrains_movieviewer.databinding.TopFragmentBinding
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
        viewModel.repo = application.app.repository
        viewModel.loadData()
        binding.lifecycleOwner = this

        val topListAdapter = MovieListAdapter(MovieDataListener{
            viewModel.onMovieClicked(it)
        })
        binding.topListRecyclerView.adapter = topListAdapter
        binding.topListRecyclerView.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.topList.observe(viewLifecycleOwner, Observer {
            it?.let{
                topListAdapter.submitList(it)
            }
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopViewModel::class.java)
    }

}