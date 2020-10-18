package com.shahzar.enoclink.ui.splash

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shahzar.enoclink.ui.main.MainActivity
import com.shahzar.enoclink.R
import com.shahzar.enoclink.ui.base.BaseActivity

class SplashActivity: BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun getLayoutRes() = R.layout.activity_splash

    override fun injectDependency() = getDiComponent().inject(this)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
        viewModel.checkLoginStatus()
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.isUserLoggedIn.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }



}