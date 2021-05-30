package com.dineshworkspace.datingapp.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseViewModel
import com.dineshworkspace.datingapp.base.GeneralInformation
import com.dineshworkspace.datingapp.base.Profile
import com.dineshworkspace.datingapp.base.ProfileResponse
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val discoverRepository: DiscoverRepository) :
    BaseViewModel() {

    val profileResponse: MutableLiveData<BaseResponse<ProfileResponse>> = MutableLiveData()

    init {
        fetchProfiles()
    }

    private fun fetchProfiles() {
        viewModelScope.launch {
            discoverRepository.fetchProfileList().collect {
                profileResponse.postValue(it)
            }
        }
    }

}