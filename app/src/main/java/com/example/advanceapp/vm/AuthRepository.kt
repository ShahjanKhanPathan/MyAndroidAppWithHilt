package com.example.advanceapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.advanceapp.dataclass.ApiInterface
import com.example.advanceapp.dataclass.NetworkClass
import com.example.advanceapp.modal.LoginResponse
import com.example.advanceapp.modal.MyLoginModal
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    private val IsAuthComplete = MutableLiveData<NetworkClass<MyLoginModal>>()

    val isAuth : LiveData<NetworkClass<MyLoginModal>>
    get() = IsAuthComplete

    suspend fun getLogin(requestBody: RequestBody){
        IsAuthComplete.postValue(NetworkClass.onLoading())
        val response = apiInterface.getLogin(requestBody)
        if (response.isSuccessful && response.body() != null){
            IsAuthComplete.postValue(NetworkClass.onSuccess(response.body()))
        }
    }
//    user_type : String,username : String,password : String
}