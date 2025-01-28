package com.example.aplikasi1.data.service

import com.example.aplikasi1.data.model.Klien

interface KlienService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @GET("klien")
    suspend fun getKlien(): KlienResponse

    @GET("klien/{idKlien}")
    suspend fun getKlienById(@Path("idKlien") idKlien: Int): KlienDetailResponse

    @POST("klien/store")
    suspend fun insertKlien(@Body klien: Klien)

    @PUT("klien/{idKlien}")
    suspend fun updateKlien(@Path("idKlien") idKlien: Int, @Body klien: Klien)

    @DELETE("klien/{idKlien}")
    suspend fun deleteKlien(@Path("idKlien") idKlien: Int): Response<Void>
}
