package com.example.obentalha.craftandroid

import android.app.Application
import com.example.obentalha.craftandroid.di.AppComponents
import com.example.obentalha.craftandroid.di.DaggerAppComponents

/**
 * Created by Oussama BENTALHA on 13/11/2018.
 */
class MainApplication : Application() {

    private lateinit var appComponents: AppComponents

    override fun onCreate() {
        super.onCreate()

        appComponents = DaggerAppComponents.builder().build()

    }

    fun getAppComponents() : AppComponents {
        return appComponents
    }

}