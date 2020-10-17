package com.shahzar.enoclink.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shahzar.enoclink.NavMgr
import com.shahzar.enoclink.R
import com.shahzar.enoclink.di.ViewModelFactory
import com.shahzar.enoclink.ui.base.BaseFragment
import com.shahzar.enoclink.ui.home.HomeFragment
import com.shahzar.enoclink.util.livedata.Status
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel>() {

    @Inject
    lateinit var navMgr: NavMgr

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun getLayoutRes() = R.layout.login_fragment
    override fun getViewModelClass() = LoginViewModel::class.java
    override fun injectDependency() = getDiComponent().inject(this)

    override fun initViews() {
        btnLogin.setOnClickListener {
            viewModel.login()
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.onLoginSuccess.observe(viewLifecycleOwner, Observer {
            it.getIfNotHandled()?.run {
                navMgr.pushFragment(activity, HomeFragment.newInstance())
            }
        })
    }

}
