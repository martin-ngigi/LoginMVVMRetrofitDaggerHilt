package com.example.loginmvvmretrofitdaggerhilt.model


import com.example.loginmvvmretrofitdaggerhilt.response.LoginResponseOTPinfo
import com.google.gson.annotations.SerializedName

data class DataModelLoginData(
    @SerializedName("UserID") val userID: Int,
    @SerializedName("OTPinfo") val oTPinfo: List<LoginResponseOTPinfo>
)
