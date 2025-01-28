package com.example.aplikasi1.data.service

import com.example.aplikasi1.data.model.Lokasi
import com.example.aplikasi1.data.model.Vendor

interface LokasiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("lokasi")
    suspend fun getLokasi(): LokasiResponse

    @GET("lokasi/{idLokasi}")
    suspend fun getLokasiById(@Path("idLokasi") idLokasi: Int): LokasiDetailResponse

    @POST("lokasi/store")
    suspend fun insertLokasi(@Body lokasi: Lokasi)

    @PUT("lokasi/{idLokasi}")
    suspend fun updateLokasi(@Path("idLokasi") idLokasi: Int, @Body lokasi: Lokasi)

    @DELETE("lokasi/{idLokasi}")
    suspend fun deleteLokasi(@Path("idLokasi") idLokasi: Int): Response<Void>
}