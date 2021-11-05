package com.magnalleexample.geekbrains_movieviewer.ui.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.magnalleexample.geekbrains_movieviewer.databinding.ListItemMovieViewBinding
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData

class MovieListAdapter(): ListAdapter<MovieData, RecyclerView.ViewHolder>(DiffCallback)  {
    companion object DiffCallback : DiffUtil.ItemCallback<MovieData>(){
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieDataViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieDataViewHolder -> {
                val item: MovieDataViewHolder = holder
                item.bind(getItem(position))
            }
        }
    }
    class MovieDataViewHolder private constructor (val binding: ListItemMovieViewBinding): RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): MovieDataViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMovieViewBinding.inflate(layoutInflater, parent, false)
                return MovieDataViewHolder(binding)
            }
        }
        fun bind(movieData: MovieData){
            binding.movieData = movieData
            binding.executePendingBindings()
        }
    }
}