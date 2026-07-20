package com.nutrisport.data.domain

import dev.gitlive.firebase.auth.FirebaseUser

interface CustomerRepository {

    suspend fun createCustomer(
        customer: FirebaseUser,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

     fun getCurrentUserId(): String?
}