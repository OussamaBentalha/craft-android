package com.example.obentalha.craftandroid

import com.example.obentalha.craftandroid.api.model.Address
import com.example.obentalha.craftandroid.api.model.User
import com.example.obentalha.craftandroid.api.service.UserServiceImpl
import com.example.obentalha.craftandroid.api.service.interfaces.UserService
import com.example.obentalha.craftandroid.model.UserUI
import com.example.obentalha.craftandroid.viewmodel.MainViewModel
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Oussama BENTALHA on 21/11/2018.
 */
class MainViewModelTest {

    lateinit var mainViewModel: MainViewModel

//    @Mock
    lateinit var userServiceImpl: UserServiceImpl

    @Mock
    lateinit var userService: UserService


    private lateinit var testObserver: TestObserver<UserUI>

    companion object {
        private val address = Address("street", "suite", "city", "zipCode")
        private val user1 = User("Oussama", "Bentalha", "obentalha@brilliantbasics.com", address)
        private val user2 = User("Selva", "Raman", "sraman@brilliantbasics.com", address)
    }


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        userServiceImpl = UserServiceImpl(userService)

        mainViewModel = MainViewModel(userServiceImpl)
    }

    @Test
    fun getFirstUserTest() {
        Mockito.`when`(userServiceImpl.getUsers()).thenReturn(Single.just(arrayListOf(user1, user2)))

        val result = UserUI(user1.username, user1.name)

        testObserver = mainViewModel.fetchFirstUser().test()
        testObserver.assertValue(result)
    }

    @Test
    fun getFirstUserTestSimpleWay() {
        Mockito.`when`(userServiceImpl.getUsers()).thenReturn(Single.just(arrayListOf(user1, user2)))

        val result = UserUI(user1.username, user1.name)

        Assert.assertEquals(result, mainViewModel.fetchFirstUser().blockingGet())
    }

    @Test
    fun getError() {
        Mockito.`when`(userServiceImpl.getUsers()).thenReturn(Single.just(arrayListOf()))

        testObserver = mainViewModel.fetchFirstUser().test()
        testObserver.assertError(Throwable::class.java)
    }


}