package com.example.aplikasi1.data.service

import com.example.aplikasi1.data.model.Lokasi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface LokasiService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("insertlokasi.php")
    suspend fun insertLokasi(@Body lokasi: Lokasi)

    @GET("getlokasi.php")
    suspend fun getAllLokasi(): List<Lokasi>

    @GET("baca1lokasi.php/{id_lokasi}")
    suspend fun getLokasiById(@Query("id_lokasi") idLokasi: String): Lokasi

    @PUT("updatelokasi.php/{id_lokasi}")
    suspend fun updateLokasi(@Query("id_lokasi") idLokasi: String, @Body lokasi: Lokasi)

    @DELETE("deletelokasi.php/{id_lokasi}")
    suspend fun deleteLokasi(@Query("id_lokasi") idLokasi: String): Response<Void>
}
