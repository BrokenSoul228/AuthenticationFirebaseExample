package com.example.myapplication.screens.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
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
    private lateinit var radio1 : RadioButton
    private lateinit var radio2 : RadioButton
    private lateinit var radio3 : RadioButton
    private lateinit var radioGroup : RadioGroup
    private lateinit var textView2 : TextView

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
        textView2 = binding.textView2
        radioGroup = binding.radioGroup

        btnLogOut.setOnClickListener {
            viewModel.logOut()
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            radio1 = binding.radio1
            radio2 = binding.radio2
            radio3 = binding.radio3

            if (radio1.isChecked){
                textView2.text = radio1.text
                textView2.setOnClickListener {
                    findNavController().navigate(R.id.action_startFragment_to_firstButtonFragment)
                }
            }
            else if(radio2.isChecked){
                textView2.text = radio2.text
                textView2.setOnClickListener {
                    findNavController().navigate(R.id.action_startFragment_to_secondButtonFragment)
                }
            }
            else if(radio3.isChecked){
                textView2.text = radio3.text
                textView2.setOnClickListener {
                    findNavController().navigate(R.id.action_startFragment_to_thirdButtonFragment)
                }
            }
            else{
                textView2.text = "Start Fragment"
            }
        }

    }
}