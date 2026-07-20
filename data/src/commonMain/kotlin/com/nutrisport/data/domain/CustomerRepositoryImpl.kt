package com.nutrisport.data.domain
import com.example.nutrisport.data.Customer
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

class CustomerRepositoryImpl : CustomerRepository {


    override suspend fun createCustomer(
        customer: FirebaseUser,// library for Google auth implementation once in KMP project
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        try{
            val customerCollection= Firebase.firestore.collection("customers")
            val customer = Customer(
                id = customer.uid,
                firstName = customer.displayName?.split("")?.firstOrNull()?:"Unknown",
                lastName = customer.displayName?.split("")?.lastOrNull()?:"Unknown",
                email = customer.email?:"Unknown",
            )

            val customerExist=customerCollection.document(customer.id).get().exists

            if (customerExist){
                onSuccess()
            }
            else{
                customerCollection.document(customer.id).set(customer)
            }

        }
        catch(e: Exception){
            onFailure(e)
        }
    }

    override  fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }
}