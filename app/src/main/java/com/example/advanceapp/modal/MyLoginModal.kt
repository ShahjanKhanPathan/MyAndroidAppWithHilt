package com.example.advanceapp.modal

data class MyLoginModal(
    var code : Int?,
    var message : String?,
    var data : Data
)

data class Data(
    var id : String,
    var first_name : String?,
    var last_name : String?,
    var phone : String?,
    var email : String?,
    var image_url : String?,
    var password : String?,
    var server_token : String?,
    var createdAt : String?,
    var updatedAt : String?,
    var __v : Int?,
)
