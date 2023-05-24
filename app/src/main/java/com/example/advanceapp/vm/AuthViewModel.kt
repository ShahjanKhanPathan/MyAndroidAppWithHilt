package com.example.advanceapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advanceapp.dataclass.NetworkClass
import com.example.advanceapp.modal.LoginResponse
import com.example.advanceapp.modal.MyLoginModal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val isAuthDone : LiveData<NetworkClass<MyLoginModal>>
    get() = repository.isAuth

    fun getLogin(requestBody: RequestBody) = viewModelScope.launch(Dispatchers.IO){
        repository.getLogin(requestBody)
    }
}