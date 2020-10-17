package com.shahzar.enoclink.ui.login

import androidx.lifecycle.Observer
import com.shahzar.enoclink.NavMgr
import com.shahzar.enoclink.R
import com.shahzar.enoclink.ui.base.BaseFragment
import com.shahzar.enoclink.ui.home.HomeFragment
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
        setTitle(getString(R.string.page_title_login))
        btnLogin.setOnClickListener {
            onLogin()
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

    private fun onLogin() {
        if (validate()) {
            viewModel.login(etUsername.text.toString(), etPassword.text.toString())
        }
    }

    private fun validate(): Boolean {
        if (etUsername.text.isNullOrEmpty()) {
            etUsername.error = getString(R.string.err_enter_username)
            return false
        }

        if (etPassword.text.isNullOrEmpty()) {
            etPassword.error = getString(R.string.err_enter_password)
            return false
        }

        return true
    }

}
