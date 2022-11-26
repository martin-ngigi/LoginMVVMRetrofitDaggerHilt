package com.example.loginmvvmretrofitdaggerhilt.repository

import com.example.loginmvvmretrofitdaggerhilt.model.DataModelLoginBody
import com.example.loginmvvmretrofitdaggerhilt.model.DataModelLoginStatus
import com.example.loginmvvmretrofitdaggerhilt.webapi.ApiService
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getLogin(dataModelLoginBody: DataModelLoginBody): Response<DataModelLoginStatus> {
        return apiService.getLogin(dataModelLoginBody)
    }
}