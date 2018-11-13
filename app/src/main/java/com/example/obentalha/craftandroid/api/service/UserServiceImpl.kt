package com.example.obentalha.craftandroid.api.service

import com.example.obentalha.craftandroid.api.model.User
import com.example.obentalha.craftandroid.api.service.interfaces.UserService
import io.reactivex.Single

/**
 * Created by Oussama BENTALHA on 11/11/2018.
 */
class UserServiceImpl(private val userService: UserService) {

    fun getUsers(): Single<List<User>> {
        return userService.getUsers()
    }

}