package com.nutrisport.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {

}
expect val targetModule: Module

fun initializeKoin(config: KoinAppDeclaration?=null){
    startKoin {
        config?.invoke(this)
        modules(sharedModule, targetModule)
    }
}