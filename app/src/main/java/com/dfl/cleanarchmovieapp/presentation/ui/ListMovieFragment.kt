package com.dfl.cleanarchmovieapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dfl.cleanarchmovieapp.databinding.FragmentListMovieBinding
import com.dfl.cleanarchmovieapp.presentation.vm.ManagementMoviesVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListMovieFragment : Fragment() {

    // propiedad es valida entre onCreateView y onDestroyView
    private var _binding: FragmentListMovieBinding? = null

    private val binding get() = _binding!!
    private val viewModel: ManagementMoviesVM by activityViewModels()
    private val adapter = ListMovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesRecyclerView.apply {
            adapter = adapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        viewModel.getAllMovies()
        viewModel.movies.observe(viewLifecycleOwner, { model ->

            when (model) {
                is UiModel.Loading -> Log.d("test", "loading")
                is UiModel.ContentMovies -> adapter.submitList(model.movies)
                else -> Log.d("test", "nothing")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}