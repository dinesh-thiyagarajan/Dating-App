package com.dineshworkspace.datingapp.base

import com.google.gson.annotations.SerializedName

data class ProfileResponse(val invites: Invites,val likes: Likes) {
}

data class Invites(
    val profiles: ArrayList<Profile>
) {

}

data class Profile(val generalInformation: GeneralInformation, val name: String) {

}

data class GeneralInformation(
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("photos") val photo: ArrayList<Photo>,
    val age: Int
)

data class Photo(
    val photo: String,
    @SerializedName("photo_id") val photoId: String,
    @SerializedName("selected") val selected: Boolean,
    @SerializedName("status") val status: String,
)

data class Likes(
    val profiles: ArrayList<LikedProfile>,
    @SerializedName("can_see_profile") val canSeeProfile: Boolean,
    @SerializedName("likes_received_count") val likedReceivedCount: Int
)

data class LikedProfile(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("avatar") val avatar: String
)