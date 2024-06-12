package com.bangkit.balisnap.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthRepostiory {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signUp(email: String, password: String): FirebaseUser? {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    suspend fun signIn(email: String, password: String): FirebaseUser? {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user
    }

    suspend fun setDisplayName(name: String, user: FirebaseUser?): FirebaseUser?{
        user?.let {
            val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(name).build()
            user.updateProfile(profileUpdates).await()
        }
        return user
    }
}