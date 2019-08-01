package com.movie.films.ui.activity

import android.os.Bundle
import com.movie.films.R
import com.movie.films.ui.fragments.LoginFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager.beginTransaction().add(R.id.loginFragmentContainer, LoginFragment()).commit()

    }
}