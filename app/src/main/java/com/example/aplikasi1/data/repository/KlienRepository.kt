package com.example.aplikasi1.data.repository

import com.example.aplikasi1.data.model.Klien
import com.example.aplikasi1.data.service.KlienService

interface KlienRepository{
    suspend fun getKlien() : KlienResponse
    suspend fun insertKlien(kamar: Klien)
    suspend fun updateKlien(idKlien: Int, klien: Klien)
    suspend fun deleteKlien(idKlien: Int)
    suspend fun getKlienById(idKlien: Int): Klien
}

class NetworkKlienRepository(
    private val klienApiService : KlienService
) : KlienRepository {
    override suspend fun getKlien(): KlienResponse {
        return try {
            klienApiService.getKlien()
        } catch (e: Exception) {
            throw IOException("Failed to fetch klien data : ${e.message}")
        }
    }

    override suspend fun insertKlien(klien: Klien) {
        klienApiService.insertKlien(klien)
    }

    override suspend fun updateKlien(idKlien: Int, klien: Klien) {
        klienApiService.updateKlien(idKlien, klien)
    }

    override suspend fun deleteKlien(idKlien: Int) {
        try {
            val response = klienApiService.deleteKlien(idKlien)
            if (!response.isSuccessful) {
                throw IOException("gagal menghapus data klien. HTTP kode: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKlienById(idKlien: Int): Klien {
        return klienApiService.getKlienById(idKlien).data
    }

}