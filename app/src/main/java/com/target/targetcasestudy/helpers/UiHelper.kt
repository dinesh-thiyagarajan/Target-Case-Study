package com.target.targetcasestudy.helpers

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.target.targetcasestudy.TargetApp

object UiHelper {

    fun showSnackBar(view: View, errMsg: String?, actionBtn: String) {
        Snackbar.make(view, errMsg!!, Snackbar.LENGTH_INDEFINITE)
            .setAction(actionBtn) {

            }
            .setActionTextColor(
                TargetApp.application.applicationContext.resources.getColor(
                    android.R.color.holo_red_light
                )
            )
            .show()
    }

    fun showSnackBarWithAutoClosure(view: View, errMsg: String?, actionBtn: String) {
        Snackbar.make(view, errMsg!!, Snackbar.LENGTH_INDEFINITE)
            .setAction(actionBtn) {

            }
            .setActionTextColor(
                TargetApp.application.applicationContext.resources.getColor(
                    android.R.color.holo_red_light
                )
            )
            .setDuration(3000)
            .show()
    }

}