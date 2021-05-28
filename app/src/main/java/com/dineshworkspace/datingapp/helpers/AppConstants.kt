package com.dineshworkspace.datingapp.helpers

object AppConstants {

    /*APIs*/
    const val BASE_URL = "https://testa2.aisle.co/V1/"
    const val END_POINT_PHONE_NUMBER_LOGIN = "users/phone_number_login"
    const val END_POINT_VERIFY_OTP = "users/verify_otp"
    const val END_POINT_PROFILE_LIST = "users/test_profile_list"

    /*API Keys*/
    const val API_KEY_NUMBER = "number"
    const val API_KEY_OTP = "otp"
    const val API_HEADER_KEY_CONTENT_TYPE = "Content-Type"
    const val API_HEADER_KEY_COOKIE = "Cookie"
    const val API_HEADER_KEY_AUTH = "Authorization"

    const val API_HEADER_VALUE_CONTENT_TYPE = "application/json"
    const val API_HEADER_VALUE_COOKIE = "cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720"

    /*Bundles*/
    const val BUNDLE_PHONE_NUMBER = "bundle_phone_number"

    /*Shared Preferences*/
    const val SHARED_PREFERENCES_NAME = "com.dineshworkspace.datingapp"
    const val PREF_IS_PHONE_VALIDATED = "is_phone_validated"
    const val PREF_API_TOKEN = "api_token"

}