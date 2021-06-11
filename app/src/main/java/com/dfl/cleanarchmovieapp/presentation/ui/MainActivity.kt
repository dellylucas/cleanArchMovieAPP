package com.dfl.cleanarchmovieapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dfl.cleanarchmovieapp.databinding.ActivityMainBinding
import com.dfl.cleanarchmovieapp.presentation.vm.ManagementMoviesVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ManagementMoviesVM by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel.getAllMovies()
        viewModel.movies.observe(this, {model->

            when (model) {
                is UiModel.Loading -> Log.d("test", "loading")
                is UiModel.ContentMovies -> {
                    model.movies.forEach {
                        Log.d("test", "movie ${it.name}")
                    }
                }
                else ->  Log.d("test", "nothing")
            }
        })
    }
}