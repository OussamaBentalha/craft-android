package com.example.obentalha.craftandroid

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.obentalha.craftandroid.api.Retrofit
import com.example.obentalha.craftandroid.api.model.User
import com.example.obentalha.craftandroid.api.service.UserServiceImpl
import com.example.obentalha.craftandroid.model.UserUI
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

    private var firstnameTV: TextView? = null
    private var lastnameTV: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MainApplication).getAppComponents().inject(this)

        firstnameTV = findViewById<TextView>(R.id.firstname)
        lastnameTV = findViewById<TextView>(R.id.lastname)
    }

    override fun onResume() {
        super.onResume()

        val fetchFirstUserDisposable = mainViewModel.fetchFirstUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { user: UserUI?, throwable: Throwable? ->
                        if(user != null ){
                            firstnameTV?.text = user.firstname
                            lastnameTV?.text = user.lastname
                        }
                }

        disposable.add(fetchFirstUserDisposable)
    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

}
