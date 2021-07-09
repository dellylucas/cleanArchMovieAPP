package com.dfl.cleanarchmovieapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dfl.cleanarchmovieapp.databinding.FragmentListMovieBinding
import com.dfl.cleanarchmovieapp.presentation.ui.adapter.ListMovieAdapter
import com.dfl.cleanarchmovieapp.presentation.vm.ManagementMoviesVM
import com.dfl.cleanarchmovieapp.utils.setVisibility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListMovieFragment : Fragment() {

    // propiedad es valida entre onCreateView y onDestroyView
    private var _binding: FragmentListMovieBinding? = null

    val binding get() = _binding!!
    private val viewModel: ManagementMoviesVM by activityViewModels()
    private val adapterMovies = ListMovieAdapter(::goToDetail)

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
            adapterMovies.submitList(movies)
            binding.swipyrefreshlayout.isRefreshing = false
        })
        viewModel.load.observe(viewLifecycleOwner, {
            binding.animationLoading.setVisibility(it)
        })

        //Scroll para  cargar nuevos elementos
        binding.swipyrefreshlayout.setOnRefreshListener {
            viewModel.getNewMovies()
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