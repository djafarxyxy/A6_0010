package com.example.aplikasi1.data.repository

import com.example.aplikasi1.data.model.Acara
import com.example.aplikasi1.data.model.AcaraResponse
import com.example.aplikasi1.data.service.AcaraService
import java.io.IOException


interface AcaraRepository {

    suspend fun insertAcara(acara: Acara)

    suspend fun getAcara(): List<Acara>

    suspend fun updateAcara(id: String, acara: Acara)

    suspend fun deleteAcara(id: String)

    suspend fun getAcarabyId(id: String): Acara
}

class NetworkAcaraRepository(
    private val acaraApiService: AcaraService
) : AcaraRepository {

    override suspend fun insertAcara(acara: Acara) {
        acaraApiService.insertAcara(acara)
    }

    override suspend fun updateAcara(id: String, acara: Acara) {
        acaraApiService.updateAcara(id, acara)
    }

    override suspend fun deleteAcara(id: String) {
        try {
            val response = acaraApiService.deleteAcara(id)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete acara. HTTP Status Code: ${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAcara(): List<Acara> =
        acaraApiService.getAllAcara()

    override suspend fun getAcarabyId(id: String): Acara {
        return acaraApiService.getAcaraById(id)
    }
}