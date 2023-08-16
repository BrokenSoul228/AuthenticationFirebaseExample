package com.example.myapplication.screens.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.service.RepositoryFireBase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    fun singInUser(email : String, password : String, context: Context){
        viewModelScope.launch {
            RepositoryFireBase().singInUser(email, password, context)
        }
    }
}