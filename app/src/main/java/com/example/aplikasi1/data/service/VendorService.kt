package com.example.aplikasi1.data.service

import com.example.aplikasi1.data.model.Vendor
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface VendorService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("insertvendor.php")
    suspend fun insertVendor(@Body vendor: Vendor)

    @GET("getvendor.php")
    suspend fun getAllVendors(): List<Vendor>

    @GET("baca1vendor.php/{id_vendor}")
    suspend fun getVendorById(@Query("id_vendor") idVendor: String): Vendor

    @PUT("updatevendor.php/{id_vendor}")
    suspend fun updateVendor(@Query("id_vendor") idVendor: String, @Body vendor: Vendor)

    @DELETE("deletevendor.php/{id_vendor}")
    suspend fun deleteVendor(@Query("id_vendor") idVendor: String): Response<Void>
}

