package com.example.obentalha.craftandroid.di

import com.example.obentalha.craftandroid.api.service.UserServiceImpl
import com.example.obentalha.craftandroid.api.service.interfaces.UserService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Oussama BENTALHA on 13/11/2018.
 */
@Module
class RetrofitModule {

    @Provides
    @Singleton
    internal fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build()
    }

    @Provides
    @Singleton
    internal fun provideUserServiceImpl(retrofit: Retrofit): UserServiceImpl {
        return UserServiceImpl(retrofit.create(UserService::class.java))
    }

}