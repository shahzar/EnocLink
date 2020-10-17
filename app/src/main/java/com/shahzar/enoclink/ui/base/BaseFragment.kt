package com.shahzar.enoclink.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.shahzar.enoclink.R
import com.shahzar.enoclink.di.ViewModelFactory
import com.shahzar.enoclink.di.components.AppComponent
import com.shahzar.enoclink.ui.base.BaseActivity
import com.shahzar.enoclink.ui.login.LoginViewModel
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>: Fragment() {

    lateinit var rootView: View
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun getLayoutRes(): Int
    abstract fun getViewModelClass(): Class<VM>
    abstract fun injectDependency()
    abstract fun initViews()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(getLayoutRes(), container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

        initViews()
        setupObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependency()
    }

    open fun setupObservers() {
        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    fun getDiComponent() : AppComponent {
        return (activity as BaseActivity).getDiComponent()
    }

    fun showError(msg: String) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show()
    }

}