package com.example.aplikasi1.data.repository

import com.example.aplikasi1.data.model.Acara
import com.example.aplikasi1.data.model.Lokasi
import com.example.aplikasi1.data.model.LokasiResponse
import com.example.aplikasi1.data.service.LokasiService
import java.io.IOException

interface LokasiRepository {

    suspend fun insertLokasi(lokasi: Lokasi)

    suspend fun getLokasi(): List<Lokasi>

    suspend fun updateLokasi(idLokasi: String, lokasi: Lokasi)

    suspend fun deleteLokasi(idLokasi: String)

    suspend fun getLokasiById(idLokasi: String): Lokasi
}

class NetworkLokasiRepository(
    private val lokasiApiService: LokasiService // Mengganti MahasiswaService dengan LokasiService
) : LokasiRepository {

    override suspend fun insertLokasi(lokasi: Lokasi) {
        lokasiApiService.insertLokasi(lokasi)
    }

    override suspend fun updateLokasi(idLokasi: String, lokasi: Lokasi) {
        lokasiApiService.updateLokasi(idLokasi, lokasi)
    }

    override suspend fun deleteLokasi(idLokasi: String) {
        try {
            val response = lokasiApiService.deleteLokasi(idLokasi)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete lokasi. HTTP Status Code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getLokasi(): List<Lokasi> =
        lokasiApiService.getAllLokasi()

    override suspend fun getLokasiById(idLokasi: String): Lokasi {
        return lokasiApiService.getLokasiById(idLokasi)
    }
}
