package com.dineshworkspace.datingapp.helpers

import com.dineshworkspace.datingapp.dataModels.Error
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject


class ErrorUtil @Inject constructor(private val retrofit: Retrofit) {

    fun parseError(
        response: Response<*>
    ): Error? {
        val converter =
            retrofit.responseBodyConverter<Error>(
                Error::class.java,
                arrayOfNulls(0)
            )
        return try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            Error()
        }
    }
}