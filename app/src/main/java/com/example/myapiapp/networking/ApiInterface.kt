package com.example.myapiapp.networking

import com.example.myapiapp.model.PhotoItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {


    @GET("photos")
    fun getPhotos(): Call<List<PhotoItem>>
}