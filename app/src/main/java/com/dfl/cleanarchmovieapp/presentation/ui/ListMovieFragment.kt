package com.dfl.cleanarchmovieapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dfl.cleanarchmovieapp.databinding.FragmentListMovieBinding
import com.dfl.cleanarchmovieapp.presentation.vm.ManagementMoviesVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListMovieFragment : Fragment() {

    // propiedad es valida entre onCreateView y onDestroyView
    private var _binding: FragmentListMovieBinding? = null

    private val binding get() = _binding!!
    private val viewModel: ManagementMoviesVM by activityViewModels()
    private val adapterMovies = ListMovieAdapter(::goToDetail)
    private var page: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesRecyclerView.adapter = adapterMovies

        viewModel.getAllMovies()
        viewModel.movies.observe(viewLifecycleOwner, { movies ->
            movies.last().page.let { if (page < it) page = it }

            adapterMovies.submitList(movies)
        })
        binding.moviesRecyclerView.addOnScrollListener(onScrollListener())
    }

    private fun onScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastItemVisible =
                (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            viewModel.getNewMovies(lastItemVisible, page)
        }
    }

    /**+
     * navegacion hacia el detalle de una pelicula seleccionada
     */
    private fun goToDetail(idMovie: Int) {
        findNavController().navigate(
            ListMovieFragmentDirections.actionListMovieFragmentToDetailFragment(idMovie)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}