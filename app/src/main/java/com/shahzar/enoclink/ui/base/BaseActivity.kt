package com.shahzar.enoclink.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.shahzar.enoclink.di.components.AppComponent
import com.shahzar.enoclink.BaseApplication
import com.shahzar.enoclink.NavMgr
import com.shahzar.enoclink.R
import com.shahzar.enoclink.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
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
        setupToolbar()
        initViews()
        setupObservers()
    }

    private fun setupToolbar() {
        findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
            setSupportActionBar(toolbar)
        }
    }

    open fun setupObservers() {

    }

    fun getDiComponent() : AppComponent {
        return (application as BaseApplication).appComponent
    }

    fun setToolbarTitle(title: String?) {
        findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
            if (title != null) {
                toolbar.visibility = View.VISIBLE
                toolbar.title = title
            } else {
                toolbar.title = ""
                toolbar.visibility = View.GONE
            }
        }
    }

}