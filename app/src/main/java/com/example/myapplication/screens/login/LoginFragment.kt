package com.example.myapplication.screens.login

import android.annotation.SuppressLint
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
import com.example.myapplication.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel
    private lateinit var pasText : TextInputEditText
    private lateinit var logText : TextInputEditText
    private lateinit var register : TextView
    private lateinit var btnSingIn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAll()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    fun initAll(){
        pasText = binding.edPassword
        logText = binding.edEmail
        register = binding.register
        btnSingIn = binding.singIn
    }

//    @SuppressLint("ResourceType")
//    override fun onStart() {
//        super.onStart()
//        register.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_registreFragment)
//        }
//        btnSingIn.setOnClickListener {
//            viewModel.singInUser(
//                logText.text.toString(),
//                pasText.text.toString(),
//                requireContext()
//            )
//            findNavController().navigate(R.id.action_loginFragment_to_startFragment2)
//        }
//    }
}