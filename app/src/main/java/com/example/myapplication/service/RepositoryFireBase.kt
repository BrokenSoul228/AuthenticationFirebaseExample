package com.example.myapplication.service

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RepositoryFireBase {
    private val auth = FirebaseAuth.getInstance()

    fun createUser(email : String, password : String, context: Context){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(context, "OK!!!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "NOT OK!!!!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun singInUser(email : String, password: String, context : Context){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(context, "OK!!!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "NOT OK!!!!", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun logOut(){
        auth.signOut()
    }
}