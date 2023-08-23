package com.example.myapplication.screens.start

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding : FragmentStartBinding
    private lateinit var viewModel : StartViewModel
    private lateinit var movieText : TextView
    private lateinit var animeText : TextView
    private lateinit var serialText : TextView
    private lateinit var movieCollage : ImageView
    private lateinit var animeCollage : ImageView
    private lateinit var serialCollage : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        movieText = binding.movieText
        animeText = binding.animeText
        serialText = binding.serialText

        movieCollage = binding.movieCollage
        animeCollage = binding.animeCollage
        serialCollage = binding.serialCollage

        movieText.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_startFragment_to_moviepage)
            }, 300)
        }
        animeText.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_animepage)
        }
        serialText.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_thirdButtonFragment)
        }
    }

}