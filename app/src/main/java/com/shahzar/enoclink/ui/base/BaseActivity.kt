package com.shahzar.enoclink.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shahzar.enoclink.di.components.AppComponent
import com.shahzar.enoclink.BaseApplication
import com.shahzar.enoclink.NavMgr
import com.shahzar.enoclink.R
import com.shahzar.enoclink.di.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun getLayoutRes(): Int
    abstract fun injectDependency()
    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        injectDependency()
        initViews()
        setupObservers()
    }

    open fun setupObservers() {

    }

    fun getDiComponent() : AppComponent {
        return (application as BaseApplication).appComponent
    }

}