package com.example.auth.presentation.signin

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.example.auth.presentation.components.AuthResponse
import com.example.cookfresh.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await


class AuthenticationManager{
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val tag= "AuthenticationManager: "
    private var credentialManager: CredentialManager? = null

    suspend fun signUpWithEmail(email:String, password: String): AuthResponse {
        return try{
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            AuthResponse.Authenticated
        } catch (e:Exception){
            AuthResponse.Error(e.message?: "Unknown Error")
        }

    }

     suspend fun signInWithEmail(email:String, password: String): AuthResponse {
         return try{
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
             AuthResponse.Authenticated
         } catch(e: Exception){
             AuthResponse.Error(e.message?:"Unknown Error")
         }
    }

    private suspend fun buildCredentialRequest(context: Context): GetCredentialResponse?{
        val request= GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()
        return credentialManager?.getCredential(context = context, request = request)
    }

    suspend fun handleGoogleSignIn(result: GetCredentialResponse): AuthResponse {
        val credential= result.credential
        if(credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){

            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                Log.d("CredentialInfo", tag+ "name: ${tokenCredential.displayName}")
                Log.d("CredentialInfo", tag+ "name: ${tokenCredential.id}")
                Log.d("CredentialInfo", tag+ "name: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
                val authResult= firebaseAuth.signInWithCredential(authCredential).await()
                if(authResult.user !=null)
                   return AuthResponse.Authenticated
                else
                    return AuthResponse.Unauthenticated

            } catch (e: GoogleIdTokenParsingException){
                Log.d("Error", tag+ "Parsing Error: ${e.message}")
                return AuthResponse.Error("Parsing Error: ${e.message}")
            }

        }else{
            Log.d("Error", tag+ "Credential is not GoogleIdTokenCredential")
            return AuthResponse.Error("Credential is not GoogleIdTokenCredential")
        }
    }

    fun isSignedIn(): AuthResponse {
        if(firebaseAuth.currentUser != null)
            return AuthResponse.Authenticated
        else
            return AuthResponse.Unauthenticated
    }


    suspend fun signInWithGoogle(context: Context): AuthResponse {
        credentialManager= CredentialManager.create(context)
        if(isSignedIn() is AuthResponse.Authenticated)
            return AuthResponse.Authenticated
        else{
            try{
                val result= buildCredentialRequest(context)
                return result?.let { handleGoogleSignIn(it) }?: AuthResponse.Error("Timed Out")
            } catch(e: Exception){
                e.printStackTrace()
                if(e is CancellationException)
                    throw e

                Log.d("Error", tag+ "SignIn Error: ${e.message}")
                return AuthResponse.Error("SignIn Error: ${e.message}")
            }
        }
    }

    suspend fun signOut(){
        credentialManager?.clearCredentialState(ClearCredentialStateRequest())
        firebaseAuth.signOut()
    }
}

