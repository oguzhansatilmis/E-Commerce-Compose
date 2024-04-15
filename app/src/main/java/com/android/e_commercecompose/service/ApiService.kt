package com.android.e_commercecompose.service


import com.android.e_commercecompose.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("products/categories")
    suspend fun getAllCategories():Response<List<String>>

    @GET("products")
    suspend fun getAllProducts():Response<List<Product>>

}