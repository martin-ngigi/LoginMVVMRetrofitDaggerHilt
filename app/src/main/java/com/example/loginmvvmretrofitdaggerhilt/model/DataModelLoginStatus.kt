package com.example.loginmvvmretrofitdaggerhilt.model

import com.google.gson.annotations.SerializedName

data class DataModelLoginStatus(
//    @SerializedName("status") val status: Int,
//    @SerializedName("data") val data: DataModelLoginData,
//    @SerializedName("message") val message: String,
//    @SerializedName("error_code") val error_code: String,
//    @SerializedName("error") val error: String,

    @SerializedName("access")
    val access: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("user")
    val user: UserLogin
)
