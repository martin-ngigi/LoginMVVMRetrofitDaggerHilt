package com.example.loginmvvmretrofitdaggerhilt.model

import com.google.gson.annotations.SerializedName

data class DataModelLoginBody(
//    @SerializedName("EmailORMobile") val emailORMobile : String,
//    @SerializedName("Password") val password : String,
//    @SerializedName("TransactionType") val transactionType : String,
//    @SerializedName("DeviceID") val deviceID : String
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,

    @SerializedName("TransactionType") val transactionType : String,
    @SerializedName("DeviceID") val deviceID : String
)
