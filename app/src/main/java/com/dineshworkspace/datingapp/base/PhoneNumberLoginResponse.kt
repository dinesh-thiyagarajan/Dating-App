package com.dineshworkspace.datingapp.base

import com.google.gson.annotations.SerializedName

data class PhoneNumberLoginResponse(@SerializedName("status") val loginStatus: Boolean)
data class OtpVerificationResponse(val token: String?)

