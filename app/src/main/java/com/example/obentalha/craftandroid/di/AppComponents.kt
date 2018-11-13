package com.example.obentalha.craftandroid.di

import com.example.obentalha.craftandroid.MainActivity
import com.example.obentalha.craftandroid.api.Retrofit
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Oussama BENTALHA on 13/11/2018.
 */
@Singleton
@Component(modules = [(MainModule::class), (RetrofitModule::class)])
interface AppComponents {
    fun inject(activity: MainActivity)
}