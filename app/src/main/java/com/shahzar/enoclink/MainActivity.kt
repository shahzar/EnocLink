package com.shahzar.enoclink

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shahzar.enoclink.ui.base.BaseActivity
import com.shahzar.enoclink.ui.home.HomeFragment
import com.shahzar.enoclink.ui.login.LoginFragment
import com.shahzar.enoclink.ui.splash.SplashViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var navMgr: NavMgr

    private lateinit var viewModel: SplashViewModel

    override fun getLayoutRes() = R.layout.activity_main

    override fun injectDependency() = getDiComponent().inject(this)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
        viewModel.checkLoginStatus()
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.isUserLoggedIn.observe(this, Observer { loggedIn ->
            if (loggedIn) {
                navMgr.pushFragment(this, HomeFragment.newInstance(), false)
            } else {
                navMgr.pushFragment(this, LoginFragment.newInstance(), false)
            }
        })
    }

    override fun onBackPressed() {
        if(!navMgr.pop(this)) {
            finish()
        }
    }
}
