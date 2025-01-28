package com.example.aplikasi1.data.repository

import com.example.aplikasi1.data.model.Lokasi


class NetworkLokasiRepository(
    private val lokasiApiService : LokasiService
) : LokasiRepository {
    override suspend fun getLokasi(): LokasiResponse {
        return try {
            lokasiApiService.getLokasi()
        } catch (e: Exception) {
            throw IOException("Failed to fetch lokasi data: ${e.message}")
        }
    }

    override suspend fun insertLokasi(lokasi: Lokasi) {
        lokasiApiService.insertLokasi(lokasi)
    }

    override suspend fun updateLokasi(idLokasi: Int, lokasi: Lokasi) {
        lokasiApiService.updateLokasi(idLokasi, lokasi)
    }

    override suspend fun deleteLokasi(idLokasi: Int) {
        try {
            val response = lokasiApiService.deletelokasi(idLokasi)
            if (!response.isSuccessful) {
                throw IOException("gagal menghapus data Lokasi. HTTP kode: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMahasiswaById(idMhs: Int): Mahasiswa {
        return mahasiswaApiService.getMahasiswaById(idMhs).data
    }
}