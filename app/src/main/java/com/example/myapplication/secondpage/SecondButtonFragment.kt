package com.example.myapplication.secondpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentSecondButtonBinding

class SecondButtonFragment : Fragment() {

    private lateinit var binding: FragmentSecondButtonBinding
    private lateinit var secondView: TextView
    private lateinit var pointerBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        secondView = binding.secondView
        pointerBack = binding.pointerBack
    }

    override fun onStart() {
        super.onStart()
        pointerBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}