package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.target.targetcasestudy.R
import com.target.targetcasestudy.helpers.UiHelper


open class BaseActivity(@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    lateinit var parentLayout: View
    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        parentLayout = findViewById(android.R.id.content)
    }

    protected fun showFragment(fragment: Fragment) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.add(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    protected fun showSnackBar(msg: String?) {
        parentLayout.let {
            UiHelper.showSnackBar(it, msg, getString(R.string.close_caps))
        }
    }

    protected fun showSnackBarWithAutoClosure(msg: String?) {
        parentLayout.let {
            UiHelper.showSnackBarWithAutoClosure(it, msg, getString(R.string.close_caps))
        }
    }
}