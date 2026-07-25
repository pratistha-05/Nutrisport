package com.nutrisport.data.domain

import com.example.nutrisport.utils.remote.NetworkResult
import dev.gitlive.firebase.auth.FirebaseUser

interface CustomerRepository {

    suspend fun createCustomer(
        customer: FirebaseUser?,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    suspend fun singout(): NetworkResult<Unit>

     fun getCurrentUserId(): String?
}