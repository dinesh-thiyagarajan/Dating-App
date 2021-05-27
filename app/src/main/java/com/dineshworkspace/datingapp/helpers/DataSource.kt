package com.dineshworkspace.datingapp.helpers

import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class DataSource @Inject constructor(
    private val apiService: ApiService,
    private val errorUtil: ErrorUtil
) {

    suspend fun loginUsingPhoneNumber(map: Map<String, String>): BaseResponse<PhoneNumberLoginResponse> {
        return getResponse(
            request = { apiService.phoneNumberLogin(map) },
            defaultErrorMessage = "Error Login"
        )
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): BaseResponse<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return BaseResponse.success(result.body())
            } else {
                val errorResponse = errorUtil.parseError(result)
                BaseResponse.error(
                    errorResponse?.errorMessage ?: defaultErrorMessage,
                    errorResponse
                )
            }
        } catch (e: Throwable) {
            BaseResponse.error(e.localizedMessage, null)
        }
    }
}