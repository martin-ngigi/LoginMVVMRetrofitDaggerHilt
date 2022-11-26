package com.example.loginmvvmretrofitdaggerhilt.webapi

import com.example.loginmvvmretrofitdaggerhilt.model.DataModelLoginBody
import com.example.loginmvvmretrofitdaggerhilt.model.DataModelLoginStatus
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    /**
     *  API GITHUB LINK : https://github.com/martin-ngigi/Waridi-Homes-JWT-API
     */
    //login
    @POST("/auth/login2/")
    suspend fun getLogin(
        @Body dataModelLoginBody: DataModelLoginBody
    ): Response<DataModelLoginStatus>
}