package com.example.obentalha.craftandroid.api

import com.example.obentalha.craftandroid.api.service.interfaces.UserService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Oussama BENTALHA on 11/11/2018.
 */
class Retrofit {

    companion object {

        private fun create() : Retrofit {
            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .build()

        }

        fun getUsersService(): UserService {
            return create().create(UserService::class.java)
        }

    }

}