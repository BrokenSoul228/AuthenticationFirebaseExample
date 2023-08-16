package com.example.myapplication.screens.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.service.RepositoryFireBase
import kotlinx.coroutines.launch

class StartViewModel : ViewModel() {
    fun logOut(){
        viewModelScope.launch{
            RepositoryFireBase().logOut()
        }
    }
}