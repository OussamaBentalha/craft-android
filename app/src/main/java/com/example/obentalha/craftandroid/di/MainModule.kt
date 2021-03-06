package com.example.obentalha.craftandroid.di

import com.example.obentalha.craftandroid.api.service.UserServiceImpl
import com.example.obentalha.craftandroid.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Oussama BENTALHA on 13/11/2018.
 */
@Module
class MainModule {

    @Provides
    internal fun provideMainViewModel(userServiceImpl: UserServiceImpl): MainViewModel {
        return MainViewModel(userServiceImpl)
    }

}
