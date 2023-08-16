package com.example.myapplication.screens.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStartBinding
import com.google.android.material.button.MaterialButton

class StartFragment : Fragment() {

    private lateinit var binding : FragmentStartBinding
    private lateinit var viewModel : StartViewModel
    private lateinit var btnLogOut : MaterialButton

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
        btnLogOut = binding.logOut
        btnLogOut.setOnClickListener {
            viewModel.logOut()
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
    }
}