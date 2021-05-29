package com.dineshworkspace.datingapp.base

import com.google.gson.annotations.SerializedName

data class ProfileResponse(val invites: Invites) {
}

data class Invites(val profiles: ArrayList<Profile>) {

}

data class Profile(val generalInformation: GeneralInformation, val name: String, val image:Int) {

}

data class GeneralInformation(@SerializedName("date_of_birth") val dateOfBirth: String) {

}