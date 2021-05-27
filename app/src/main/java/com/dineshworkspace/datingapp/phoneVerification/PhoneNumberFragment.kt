package com.dineshworkspace.datingapp.phoneVerification

import android.os.Bundle
import android.view.View
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_phone_number.*


class PhoneNumberFragment : BaseFragment(layoutId = R.layout.fragment_phone_number) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bnt_continue.setOnClickListener {
            onContinueClicked()
        }
    }

    private fun onContinueClicked() {

    }


}