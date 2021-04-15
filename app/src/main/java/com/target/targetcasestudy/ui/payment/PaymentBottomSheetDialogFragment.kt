package com.target.targetcasestudy.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.Validators
import kotlinx.android.synthetic.main.dialog_payment.*

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private object HOLDER {
        val INSTANCE = PaymentBottomSheetDialogFragment()
    }

    companion object {
        val instance: PaymentBottomSheetDialogFragment by lazy { HOLDER.INSTANCE }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancel.setOnClickListener { dismiss() }
        submit.setOnClickListener { dismiss() }

        et_credit_card.doAfterTextChanged {
            it?.let {
                if (!it.isNullOrEmpty()) {
                    val isValid = Validators.validateCreditCard(it.toString())
                    if (isValid) {
                        submit.isEnabled = true
                        tv_error.visibility = View.GONE
                    } else {
                        submit.isEnabled = false
                        tv_error.visibility = View.VISIBLE
                    }
                } else {
                    tv_error.visibility = View.VISIBLE
                    tv_error.text = getString(R.string.valid_credit_card)
                    submit.isEnabled = false
                }
            }
        }
    }

}