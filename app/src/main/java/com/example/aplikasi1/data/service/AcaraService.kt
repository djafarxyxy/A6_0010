package com.example.aplikasi1.data.service

import com.example.aplikasi1.data.model.Acara

interface AcaraService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("acara")
    suspend fun getAcara(): AcaraResponse

    @GET("acara/{idAcara}")
    suspend fun getAcaraById(@Path("idAcara") idAcara: Int): AcaraDetailResponse

    @POST("acara/store")
    suspend fun insertAcara(@Body acara: Acara)

    @PUT("acara/{idAcara}")
    suspend fun updateAcara(@Path("idAcara") idAcara: Int, @Body acara: Acara)

    @DELETE("acara/{idAcara}")
    suspend fun deleteAcara(@Path("idAcara") idAcara: Int): Response<Void>
}