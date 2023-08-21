package com.example.myapplication.screens.firstpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFirstButtonBinding

class FirstButtonFragment : Fragment() {

    private lateinit var binding: FragmentFirstButtonBinding
    private lateinit var firstView: TextView
    private lateinit var pointerBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstButtonBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstView = binding.firstView
        pointerBack = binding.pointerBack
    }

    override fun onStart() {
        super.onStart()
        pointerBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}