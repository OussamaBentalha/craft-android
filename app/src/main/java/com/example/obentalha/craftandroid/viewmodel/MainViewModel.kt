package com.example.obentalha.craftandroid.viewmodel

import com.example.obentalha.craftandroid.api.model.User
import com.example.obentalha.craftandroid.api.service.UserServiceImpl
import io.reactivex.Single

/**
 * Created by Oussama BENTALHA on 09/10/2018.
 */
class MainViewModel {

    fun fetchUsers(userServiceImpl: UserServiceImpl): Single<List<User>> {
        return userServiceImpl.getUsers()
    }

}