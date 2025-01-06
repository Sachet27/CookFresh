package com.example.auth.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.presentation.components.AuthResponse
import com.example.auth.presentation.signin.AuthenticationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val authenticationManager: AuthenticationManager= AuthenticationManager()
    private val _state = MutableStateFlow(AuthState(isSignedIn= authenticationManager.isSignedIn()))
    val state= _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), AuthState(isSignedIn= authenticationManager.isSignedIn()))

    fun onAction(action: AuthActions){
        when(action){
            is AuthActions.SignIn-> signIn(action.context)
            is AuthActions.SignOut-> signOut()
            is AuthActions.OnEmailChange-> changeEmail(action.newEmail)
            is AuthActions.OnPasswordChange-> changePassword(action.newPassword)
            is AuthActions.OnSignUpWithEmail-> signUpWithEmail(action.context)
            is AuthActions.OnSignInWithEmail-> signInWithEmail(action.context)
            is AuthActions.OnSignOut-> signOut()
        }
    }

    private fun signOut(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isSignedIn = AuthResponse.Unauthenticated
                )
            }
            authenticationManager.signOut()

        }
    }

     private fun signIn(context: Context){
        viewModelScope.launch {
            val result= authenticationManager.signInWithGoogle(context)
            _state.update {
                it.copy(
                    isSignedIn = result
                )
            }
        }
    }

    private fun signUpWithEmail(context: Context){
        val info= _state.value
        viewModelScope.launch {
            val response= authenticationManager.signUpWithEmail(info.email, info.password)
            if(response is AuthResponse.Error){
                Toast.makeText(
                    context,
                    "Invalid Credentials",
                    Toast.LENGTH_SHORT
                ).show()
            }
            _state.update {
                it.copy(
                    isSignedIn = response,
                    email = "",
                    password = ""
                )
            }
        }
    }

    private fun signInWithEmail(context: Context){
        val info= _state.value
        viewModelScope.launch {
            val response= authenticationManager.signInWithEmail(info.email, info.password)
            if(response is AuthResponse.Error){
                Toast.makeText(
                    context,
                    "Invalid Credentials",
                    Toast.LENGTH_SHORT
                ).show()
            }
            _state.update {
                it.copy(
                    isSignedIn = response,
                    email = "",
                    password = ""
                )
            }
        }
    }

    private fun changeEmail(newEmail: String){
        _state.update {
            it.copy(
                email = newEmail
            )
        }
    }

    private fun changePassword(newPassword: String){
        _state.update {
            it.copy(
                password = newPassword
            )
        }
    }
}