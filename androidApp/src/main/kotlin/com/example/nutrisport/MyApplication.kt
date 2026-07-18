package com.example.nutrisport

import android.app.Application
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.nutrisport.di.initializeKoin
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            config = {
                androidContext(this@MyApplication)
            }
        )
        GoogleAuthProvider.create(
            credentials = GoogleAuthCredentials(serverId = "978104451715-0mgu12rpnjg2o4504d065r272imh5tbs.apps.googleusercontent.com")
        )
        Firebase.initialize(context = this)
    }
}