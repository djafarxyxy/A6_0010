package com.example.aplikasi1.data.service

import com.example.aplikasi1.data.model.Klien
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface KlienService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("insertklien.php")
    suspend fun insertKlien(@Body klien: Klien)

    @GET("getklien.php")
    suspend fun getAllKlien(): List<Klien>

    @GET("baca1klien.php/{id_klien}")
    suspend fun getKlienById(@Query("id_klien") idKlien: String): Klien

    @PUT("updateklien.php/{id_klien}")
    suspend fun updateKlien(@Query("id_klien") idKlien: String, @Body klien: Klien)

    @DELETE("deleteklien.php/{id_klien}")
    suspend fun deleteKlien(@Query("id_klien") idKlien: String): Response<Void>
}
