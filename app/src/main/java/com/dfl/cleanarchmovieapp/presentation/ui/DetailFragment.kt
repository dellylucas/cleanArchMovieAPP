package com.dfl.cleanarchmovieapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.dfl.cleanarchmovieapp.databinding.FragmentDetailBinding
import com.dfl.cleanarchmovieapp.presentation.vm.ManagementMoviesVM
import com.dfl.cleanarchmovieapp.utils.Constants
import com.dfl.cleanarchmovieapp.utils.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    // propiedad es valida entre onCreateView y onDestroyView
    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ManagementMoviesVM by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idMovie = args.idMovie
        viewModel.getMovieById(idMovie)
        viewModel.currentMovie.observe(viewLifecycleOwner, { movie ->
            movie.posterPath?.let { binding.detailImage.loadUrl(Constants.BASE_URL_MOVIES_DB_IMAGE_HIGH + it) }

            binding.titleTextView.text = movie.name
            binding.detailTextView.text = movie.description

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}