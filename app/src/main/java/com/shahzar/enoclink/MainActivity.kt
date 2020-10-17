package com.shahzar.enoclink

import com.shahzar.enoclink.ui.base.BaseActivity
import com.shahzar.enoclink.ui.login.LoginFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var navMgr: NavMgr

    override fun getLayoutRes() = R.layout.activity_main

    override fun injectDependency() = getDiComponent().inject(this)

    override fun initViews() {
        navMgr.pushFragment(this, LoginFragment.newInstance())
    }

    override fun onBackPressed() {
        if(!navMgr.pop(this)) {
            finish()
        }
    }
}
