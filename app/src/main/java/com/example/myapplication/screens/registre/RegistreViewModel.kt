package com.example.myapplication.screens.registre

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.service.RepositoryFireBase
import kotlinx.coroutines.launch

class RegistreViewModel : ViewModel() {
    fun registerNewUser(email : String, password : String, context: Context){
        viewModelScope.launch {
            RepositoryFireBase().createUser(email, password, context)
        }
    }
}