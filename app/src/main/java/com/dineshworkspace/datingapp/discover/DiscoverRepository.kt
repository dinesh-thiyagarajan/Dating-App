package com.dineshworkspace.datingapp.discover

import com.dineshworkspace.datingapp.base.ProfileResponse
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.helpers.AppConstants
import com.dineshworkspace.datingapp.helpers.DataSource
import com.dineshworkspace.datingapp.helpers.SharedPrefHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DiscoverRepository @Inject constructor(private val dataSource: DataSource) {

    suspend fun fetchProfileList(): Flow<BaseResponse<ProfileResponse>?> {
        return flow {
            emit(BaseResponse.loading(null))
            val headerMap = mapOf(
                AppConstants.API_HEADER_KEY_AUTH to SharedPrefHelper.getString(
                    AppConstants.PREF_API_TOKEN,
                    ""
                )!!,
            )
            val result = dataSource.fetchProfileList(headerMap = headerMap)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


}