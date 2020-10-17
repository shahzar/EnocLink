package com.shahzar.enoclink

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.shahzar.enoclink.ui.base.BaseActivity
import com.shahzar.enoclink.ui.base.BaseFragment


class NavMgr() {

    fun pushFragment(activity: FragmentActivity?, fragment: BaseFragment<*>) {
        (activity as? BaseActivity)?.let {
            pushFragment(it, fragment)
        }
    }

    fun pushFragment(baseActivity: BaseActivity, baseFragment: BaseFragment<*>) {
        baseActivity
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.content, baseFragment)
            .commit()
    }


    fun pop(baseActivity: BaseActivity): Boolean {

        if (baseActivity.supportFragmentManager.backStackEntryCount <= 1) {
            return false
        }

        baseActivity
            .supportFragmentManager
            .popBackStack()

        return true
    }
}