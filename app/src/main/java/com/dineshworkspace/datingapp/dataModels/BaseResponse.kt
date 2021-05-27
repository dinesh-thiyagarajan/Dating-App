package com.dineshworkspace.datingapp.dataModels


data class BaseResponse<out T>(
    val status: Status,
    val data: T?,
    val error: Error?,
    val message: String?
) {
    companion object {

        fun <T> success(data: T?): BaseResponse<T> {
            return BaseResponse(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msg: String?, error: Error?): BaseResponse<T> {
            return BaseResponse(Status.ERROR, null, error, msg)
        }

        fun <T> loading(data: T?): BaseResponse<T> {
            return BaseResponse(Status.LOADING, data, null, null)
        }

    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}