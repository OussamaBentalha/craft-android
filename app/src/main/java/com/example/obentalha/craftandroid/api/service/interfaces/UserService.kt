package com.example.obentalha.craftandroid.api.service.interfaces

import com.example.obentalha.craftandroid.api.model.User
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Oussama BENTALHA on 11/11/2018.
 */
interface UserService {

    @GET("/users")
    fun getUsers(): Single<List<User>>

}