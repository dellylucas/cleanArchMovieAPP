package com.dfl.cleanarchmovieapp.presentation.ui

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dfl.cleanarchmovieapp.databinding.FragmentListMovieBinding
import com.dfl.cleanarchmovieapp.presentation.vm.ManagementMoviesVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListMovieFragment : Fragment() {

    // propiedad es valida entre onCreateView y onDestroyView
    private var _binding: FragmentListMovieBinding? = null

    private val binding get() = _binding!!
    private val viewModel: ManagementMoviesVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllMovies()
        viewModel.movies.observe(viewLifecycleOwner, { model ->

            when (model) {
                is UiModel.Loading -> Log.d("test", "loading")
                is UiModel.ContentMovies -> {
                    binding.productsRecyclerView.adapter= ArrayAdapter(requireContext(), R.layout.simple_list_item_1, model.movies.map { ut->ut.name });
                    model.movies.forEach {
                        Log.d("test", "movie ${it.name}")
                    }
                }
                else -> Log.d("test", "nothing")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}