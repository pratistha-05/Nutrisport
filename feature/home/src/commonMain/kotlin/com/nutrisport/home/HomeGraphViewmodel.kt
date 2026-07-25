package com.nutrisport.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrisport.data.domain.CustomerRepository
import com.example.nutrisport.utils.remote.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeGraphViewmodel(
    private val customerRepository: CustomerRepository
): ViewModel(){

    private val _signOutState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Idle)
    val signOutState = _signOutState.asStateFlow()

    fun signout(){
        viewModelScope.launch { 
            _signOutState.value = NetworkResult.Loading
            val res = withContext(Dispatchers.IO){
                customerRepository.singout()  
            }
            _signOutState.value = res
        }
    }
}
