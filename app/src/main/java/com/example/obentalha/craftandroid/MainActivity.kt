package com.example.obentalha.craftandroid

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.obentalha.craftandroid.api.Retrofit
import com.example.obentalha.craftandroid.api.model.User
import com.example.obentalha.craftandroid.api.service.UserServiceImpl
import com.example.obentalha.craftandroid.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Oussama BENTALHA on 11/11/2018.
 */
class MainActivity : Activity() {

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MainApplication).getAppComponents().inject(this)
    }

    override fun onResume() {
        super.onResume()

//        var mainViewModel = MainViewModel()

        disposable.add(mainViewModel.fetchUsers(UserServiceImpl(Retrofit.getUsersService()))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { users: List<User>?, throwable: Throwable? ->
                    if(users != null && users.isNotEmpty()){
                        var userOne = users[0]
                        findViewById<TextView>(R.id.firstname).text = userOne.username
                        findViewById<TextView>(R.id.lastname).text = userOne.name
                        findViewById<TextView>(R.id.dateOfBirth).text = userOne.email
                        findViewById<TextView>(R.id.address).text = userOne.address.city
                    }
                }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}
