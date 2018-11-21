package com.example.obentalha.craftandroid.viewmodel

import com.example.obentalha.craftandroid.api.model.User
import com.example.obentalha.craftandroid.api.service.UserServiceImpl
import com.example.obentalha.craftandroid.model.UserUI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Oussama BENTALHA on 09/10/2018.
 */
class MainViewModel(private var userServiceImpl: UserServiceImpl) {

    fun fetchUsers(): Single<List<User>> {
        return userServiceImpl.getUsers()
    }

    fun fetchFirstUser(): Single<UserUI> {
        return userServiceImpl.getUsers()
                .flatMap {
                    if (it.isNotEmpty()) {
                        Single.just(convertToUserUI(it[0]))
                    } else {
                        Single.error(Throwable())
                    }
                }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }


    private fun convertToUserUI(user: User): UserUI {
        return UserUI(user.username, user.name)
    }

}