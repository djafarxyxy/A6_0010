package com.example.aplikasi1.data.di

import com.example.aplikasi1.data.repository.AcaraRepository
import com.example.aplikasi1.data.repository.KlienRepository
import com.example.aplikasi1.data.repository.LokasiRepository
import com.example.aplikasi1.data.repository.NetworkAcaraRepository
import com.example.aplikasi1.data.repository.NetworkKlienRepository
import com.example.aplikasi1.data.repository.NetworkLokasiRepository
import com.example.aplikasi1.data.repository.NetworkVendorRepository
import com.example.aplikasi1.data.repository.VendorRepository
import com.example.aplikasi1.data.service.AcaraService
import com.example.aplikasi1.data.service.KlienService
import com.example.aplikasi1.data.service.LokasiService
import com.example.aplikasi1.data.service.VendorService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val acaraRepository: AcaraRepository
    val lokasiRepository: LokasiRepository
    val klienRepository: KlienRepository
    val vendorRepository: VendorRepository
}

class AppContainerImpl : AppContainer {

    private val baseUrl = "http://10.0.2.2/dataAcr/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // Service
    private val acaraService: AcaraService by lazy {
        retrofit.create(AcaraService::class.java)
    }


    private val lokasiService: LokasiService by lazy {
        retrofit.create(LokasiService::class.java)
    }


    private val klienService: KlienService by lazy {
        retrofit.create(KlienService::class.java)
    }

    private val vendorService: VendorService by lazy {
        retrofit.create(VendorService::class.java)
    }

    // Repositories
    override val acaraRepository: AcaraRepository by lazy {
        NetworkAcaraRepository(acaraService)
    }


    override val lokasiRepository: LokasiRepository by lazy {
        NetworkLokasiRepository(lokasiService)
    }

    override val klienRepository: KlienRepository by lazy {
        NetworkKlienRepository(klienService)
    }

    override val vendorRepository: VendorRepository by lazy {
        NetworkVendorRepository(vendorService)
    }
}
