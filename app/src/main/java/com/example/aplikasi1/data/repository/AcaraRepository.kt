package com.example.aplikasi1.data.repository

import com.example.aplikasi1.data.model.Acara
import com.example.aplikasi1.data.service.AcaraService
import java.io.IOException


class NetworkAcaraRepository(
    private val acaraApiService : AcaraService
) : AcaraRepository {
    override suspend fun getAcara(): AcaraResponse {
        return try {
            acaraApiService.getAcara()
        } catch (e: Exception) {
            throw IOException("Failed to fetch acara data : ${e.message}")
        }
    }

    override suspend fun insertAcara(acara: Acara) {
        acaraApiService.insertAcara(acara)
    }

    override suspend fun updateAcara(idacara: Int, acara: Acara) {
        acaraApiService.updateAcara(idAcara, acara)
    }

    override suspend fun deleteAcara(idAcara: Int) {
        try {
            val response = acaraApiService.deleteAcara(idAcara)
            if (!response.isSuccessful) {
                throw IOException("gagal menghapus data Acara. HTTP kode: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAcaraById(idAcara: Int): Acara {
        return acaraApiService.getAcaraById(idAcara).data
    }

}