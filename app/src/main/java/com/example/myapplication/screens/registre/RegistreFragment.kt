package com.example.myapplication.screens.registre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRegistreBinding
import com.example.myapplication.databinding.FragmentStartBinding
import com.google.android.material.textfield.TextInputEditText

class RegistreFragment : Fragment() {

    private lateinit var binding : FragmentRegistreBinding
    private lateinit var viewModel : RegistreViewModel
    private lateinit var regBtn : Button
    private lateinit var singView : TextView
    private lateinit var edEmail : TextInputEditText
    private lateinit var edPassword : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registre, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RegistreViewModel :: class.java]
        regBtn = binding.btnSingIn
        singView = binding.singIn
        edEmail = binding.edEmail
        edPassword = binding.edPassword
    }

    override fun onStart() {
        super.onStart()
        regBtn.setOnClickListener {
            viewModel.registerNewUser(
                edEmail.text.toString(), edPassword.text.toString(), requireContext()
            )
            findNavController().navigate(R.id.action_registreFragment_to_startFragment)
        }
        singView.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}