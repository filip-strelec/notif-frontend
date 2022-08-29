package com.example.notifapp.JsonModels

import com.google.gson.annotations.SerializedName

data class UserJsonModel(
    @SerializedName("id")
    var id: String?,

    @SerializedName("password")
    var password: String?,

    @SerializedName("unique_hash")
    var unique_hash: String?,

    @SerializedName("user_name")
    var user_name: String?
)

