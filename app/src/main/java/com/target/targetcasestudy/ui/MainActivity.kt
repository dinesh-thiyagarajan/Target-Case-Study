package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.deals.DealsListFragment
import com.target.targetcasestudy.ui.payment.PaymentBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_toolbar.*

@AndroidEntryPoint
class MainActivity : BaseActivity(layoutId = R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(DealsListFragment())
        toolBarCustomizations()
        btn_back.setOnClickListener {
            onBackPressed()
            toggleBackButtonVisibility(View.GONE)
        }
    }

    private fun toolBarCustomizations() {
        toolbar_top.inflateMenu(R.menu.menu_main)
        toolbar_top.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.credit_card -> onCreditCardClicked()
            }
            true
        }
    }

    private fun onCreditCardClicked() {
        PaymentBottomSheetDialogFragment.instance.show(
            supportFragmentManager,
            "CreditCardValidation"
        )
    }

    fun switchFragment(fragment: Fragment) {
        showFragment(fragment = fragment)
    }

    override fun onBackPressed() {
        toggleBackButtonVisibility(View.GONE)
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            finish()
        }
    }

    fun toggleBackButtonVisibility(visibility: Int) {
        btn_back?.visibility = visibility
    }

    fun showErrorSnackbar(msg: String?) {
        showSnackBar(msg)
    }
}
