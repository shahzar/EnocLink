package com.shahzar.enoclink.di.components

import android.app.Application
import com.shahzar.enoclink.ui.main.MainActivity
import com.shahzar.enoclink.di.ViewModelBuilder
import com.shahzar.enoclink.di.modules.AppModule
import com.shahzar.enoclink.di.modules.NetworkModule
import com.shahzar.enoclink.ui.home.ProfileFragment
import com.shahzar.enoclink.ui.login.LoginFragment
import com.shahzar.enoclink.ui.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelBuilder::class])
interface AppComponent {

    fun application(application: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(profileFragment: ProfileFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(splashActivity: SplashActivity)

}