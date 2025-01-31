package com.example.aplikasi1.data.repository

import com.example.aplikasi1.data.model.Vendor
import com.example.aplikasi1.data.model.VendorResponse
import com.example.aplikasi1.data.service.VendorService
import java.io.IOException


interface VendorRepository {
    suspend fun insertVendor(vendor: Vendor)

    suspend fun getVendors(): List<Vendor>

    suspend fun updateVendor(idVendor: String, vendor: Vendor)

    suspend fun deleteVendor(idVendor: String)

    suspend fun getVendorById(idVendor: String): Vendor
}

class NetworkVendorRepository(
    private val vendorApiService: VendorService
) : VendorRepository {
    override suspend fun insertVendor(vendor: Vendor) {
        vendorApiService.insertVendor(vendor)
    }

    override suspend fun updateVendor(idVendor: String, vendor: Vendor) {
        vendorApiService.updateVendor(idVendor, vendor)
    }

    override suspend fun deleteVendor(idVendor: String) {
        try {
            val response = vendorApiService.deleteVendor(idVendor)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete vendor. HTTP Status Code: ${response.code()}"
                )
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getVendors(): List<Vendor> =
        vendorApiService.getAllVendors()

    override suspend fun getVendorById(idVendor: String): Vendor {
        return vendorApiService.getVendorById(idVendor)
    }
}
