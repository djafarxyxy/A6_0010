package com.example.aplikasi1.data.service

import com.example.aplikasi1.data.model.Vendor
import com.example.aplikasi1.data.model.VendorDetailResponse
import com.example.aplikasi1.data.model.VendorResponse
import kotlinx.coroutines.delay

interface VendorService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("vendor")
    suspend fun getVendor(): VendorResponse

    @GET("vendor/{idVendor}")
    suspend fun getVendorById(@Path("idVendor") idVendor: Int): VendorDetailResponse

    @POST("vendor/store")
    suspend fun insertVendor(@Body vendor: Vendor)

    @PUT("vendor/{idVendor}")
    suspend fun updateVendor(@Path("idVendor") idVendor: Int, @Body vendor: Vendor)

    @DELETE("vendor/{idVendor}")
    suspend fun deleteVendor(@Path("idVendor") idVendor: Int): Response<Void>
}
