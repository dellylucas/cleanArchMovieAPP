package com.dfl.cleanarchmovieapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dfl.cleanarchmovieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IBaseActivity {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun setLoading(isVisible: Boolean) {
        val visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.backgroundView.visibility = visibility
        binding.animationView.visibility = visibility
    }
}