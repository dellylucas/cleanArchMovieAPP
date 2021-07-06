package com.dfl.cleanarchmovieapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dfl.cleanarchmovieapp.databinding.AdapterListMovieBinding
import com.dfl.model.Movie

class ListMovieAdapter(val listener: (Int) -> Unit) :
    PagingDataAdapter<Movie, RecyclerView.ViewHolder>(UIMODEL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            AdapterListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            (holder as ListViewHolder).bind(movie)
            holder.itemView.setOnClickListener { listener(movie.id) }
        }
    }

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    inner class ListViewHolder(private val view: AdapterListMovieBinding) :
        RecyclerView.ViewHolder(view.root) {


        fun bind(movie: Movie) {
            view.movie = movie
        }
    }


}