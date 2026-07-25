package com.nutrisport.di

import com.nutrisport.auth.ui.AuthViewModel
import com.nutrisport.data.domain.CustomerRepository
import com.nutrisport.data.domain.CustomerRepositoryImpl
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
single<CustomerRepository>{ CustomerRepositoryImpl() }
    viewModelOf(::AuthViewModel)

}
expect val targetModule: Module

fun initializeKoin(config: KoinAppDeclaration?=null){
    startKoin {
        config?.invoke(this)
        modules(sharedModule, targetModule)
    }
}