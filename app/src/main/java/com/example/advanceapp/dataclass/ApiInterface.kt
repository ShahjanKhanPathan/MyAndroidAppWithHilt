package com.example.advanceapp.dataclass

import com.example.advanceapp.modal.LoginResponse
import com.example.advanceapp.modal.MyLoginModal
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @POST(Consts.LOGIN)
    suspend fun getLogin(
       @Body request : RequestBody
    ) : Response<MyLoginModal?>
}