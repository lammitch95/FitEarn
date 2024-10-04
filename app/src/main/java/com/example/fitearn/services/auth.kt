package com.example.fitearn.services

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import com.example.fitearn.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.util.Properties

object AuthService {

    private lateinit var auth: FirebaseAuth
    private const val RC_SIGN_IN = 9001

    // Initialize Firebase Auth
    fun initializeFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    // Dynamically retrieve the GoogleSignInClient when needed
    fun getGoogleSignInClient(activity: Activity): GoogleSignInClient {
        // Load client ID from local.properties
        val clientId = getGoogleClientId(activity)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)  // Use the client ID loaded from local.properties
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(activity, gso)
    }

    // Helper method to load the Google Client ID from local.properties
    private fun getGoogleClientId(activity: Activity): String {
        val properties = Properties()
        activity.assets.open("local.properties").use { inputStream ->
            properties.load(inputStream)
        }
        return properties.getProperty("GOOGLE_CLIENT_ID")
            ?: throw IllegalStateException("GOOGLE_CLIENT_ID not found in local.properties")
    }

    // Trigger Google Sign-In
    fun signInWithGoogle(activity: Activity) {
        val signInIntent = getGoogleSignInClient(activity).signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // Handle Google Sign-In Result
    fun handleSignInResult(
        activity: Activity,
        result: ActivityResult,
        onSuccess: (GoogleSignInAccount?) -> Unit,
        onFailure: (Exception?) -> Unit
    ) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                firebaseAuthWithGoogle(account, onSuccess, onFailure)
            }
        } catch (e: ApiException) {
            onFailure(e)
            Log.w("AuthService", "Google sign-in failed", e)
        }
    }

    // Firebase Authentication with Google Account
    private fun firebaseAuthWithGoogle(
        account: GoogleSignInAccount,
        onSuccess: (GoogleSignInAccount?) -> Unit,
        onFailure: (Exception?) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign-in successful
                    onSuccess(account)
                } else {
                    // If sign-in fails
                    onFailure(task.exception)
                }
            }
    }

    // Sign out from Google
    fun signOut(activity: Activity) {
        auth.signOut()
        getGoogleSignInClient(activity).signOut()
    }

    // Get the currently signed-in user
    fun getCurrentUser() = auth.currentUser
}
