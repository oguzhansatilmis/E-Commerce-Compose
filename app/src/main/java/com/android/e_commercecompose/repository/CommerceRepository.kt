package com.android.e_commercecompose.repository


import com.android.e_commercecompose.service.ApiService
import javax.inject.Inject

class CommerceRepository @Inject constructor(
    private val apiService: ApiService
)  {


    suspend fun getCategories() = apiService.getAllCategories()


    suspend fun getAllProduct() =   apiService.getAllProducts()



}