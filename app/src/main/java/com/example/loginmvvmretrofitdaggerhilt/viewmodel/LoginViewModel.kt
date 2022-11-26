package com.example.loginmvvmretrofitdaggerhilt.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginmvvmretrofitdaggerhilt.app.LoginApp
import com.example.loginmvvmretrofitdaggerhilt.model.DataModelLoginBody
import com.example.loginmvvmretrofitdaggerhilt.model.DataModelLoginStatus
import com.example.loginmvvmretrofitdaggerhilt.repository.LoginRepository
import com.example.loginmvvmretrofitdaggerhilt.utils.Event
import com.example.loginmvvmretrofitdaggerhilt.utils.Resource
import com.example.loginmvvmretrofitdaggerhilt.utils.hasInternetConnection
import com.example.loginmvvmretrofitdaggerhilt.utils.toast

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val repository: LoginRepository
) :
    AndroidViewModel(application) {

    val TAG="LoginPage"

    private val _loginData = MutableLiveData<Event<Resource<DataModelLoginStatus>>>()

    val loginData: LiveData<Event<Resource<DataModelLoginStatus>>> = _loginData

    fun loginUser(dataModelLoginBody: DataModelLoginBody) = viewModelScope.launch {
        getLogin(dataModelLoginBody)
    }

    suspend fun getLogin(dataModelLoginBody: DataModelLoginBody) {
        Log.d(TAG, "0, AM HERE")
        _loginData.postValue(Event(Resource.Loading()))
        Log.d(TAG, "1, AM HERE")
        try {
            if (hasInternetConnection<LoginApp>()) {
                Log.d(TAG, "2, AM HERE")
                val response = repository.getLogin(dataModelLoginBody)
                if (response.isSuccessful) {
                    Log.d(TAG, "3, AM HERE")
                    if (response.body()!!.access.isNotEmpty()) {
                        Log.d(TAG, "4, AM HERE")
                        val successresponse: DataModelLoginStatus? = response.body()
                        toast(getApplication(), successresponse!!.message)
                        _loginData.postValue(Event(Resource.Success(response.body()!!)))
                        Log.d(TAG, "Success login in : "+response.body()!!.access)
                    }
//                    else if (response.body()!!.refresh.isEmpty()) {
//
//                        val errorresponse: DataModelLoginStatus? = response.body()
//                        //toast(getApplication(), errorresponse!!.error)
//
//                    }
                    else if (response.body()!!.access.isEmpty()) {

                        val errorresponse: DataModelLoginStatus? = response.body()
                        toast(getApplication(), "ERROR LOGGIN IN")
                        Log.e("LoginPage", "ERROR LOGGIN IN: ", )
                    }

                } else {
                    _loginData.postValue(Event(Resource.Error(response.message())))
                    Log.d(TAG, "Error: "+response.message())
                }
            } else {
                _loginData.postValue(Event(Resource.Error("No Internet Connection")))
                toast(getApplication(), "No Internet Connection.!")
                Log.e(TAG, "No Internet Connection: " )
            }
        } catch (e: HttpException) {
            when (e) {
                is IOException -> {
                    _loginData.postValue(Event(Resource.Error(e.message!!)))
                    toast(getApplication(), "Exception ${e.message}")
                    Log.e(TAG, "Exception ${e.message}" )
                }

            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _loginData.postValue(Event(Resource.Error(t.message!!)))
                    toast(getApplication(), t.message!!)
                    Log.e(TAG, "Error"+t.message!! )
                }

            }

        }
    }
}