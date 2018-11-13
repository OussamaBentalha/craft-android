package com.example.obentalha.craftandroid.di

import com.example.obentalha.craftandroid.api.Retrofit
import com.example.obentalha.craftandroid.api.service.UserServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Oussama BENTALHA on 13/11/2018.
 */
@Module
class RetrofitModule {

    @Provides
    @Singleton
    internal fun provideUserServiceImpl(): UserServiceImpl {
        return UserServiceImpl(Retrofit.getUsersService())
    }

}