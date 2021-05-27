package com.dineshworkspace.datingapp.network

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun loginUsingPhoneNumber(params: Map<String, String>) =
        apiService.phoneNumberLogin(params)
}