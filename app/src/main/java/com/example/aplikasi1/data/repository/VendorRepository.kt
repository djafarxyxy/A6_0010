package com.example.aplikasi1.data.repository

import com.example.aplikasi1.data.model.Vendor
import com.example.aplikasi1.data.model.VendorResponse
import com.example.aplikasi1.data.service.VendorService

class NetworkVendorRepository(
    private val vendorApiService : VendorService
) : VendorRepository {
    override suspend fun getVendor(): VendorResponse {
        return try {
            vendorApiService.getVendor()
        } catch (e: Exception) {
            throw IOException("Failed to fetch Vendor data: ${e.message}")
        }
    }

    override suspend fun insertVendor(vendor: Vendor) {
        vendorApiService.insertVendor(vendor)
    }

    override suspend fun updateVendor(idVendor: Int, vendor: Vendor) {
        vendorApiService.updateVendor(idVendor, vendor)
    }

    override suspend fun deleteVendor(idVendor: Int) {
        try {
            val response = vendorApiService.deleteVendor(idVendor)
            if (!response.isSuccessful) {
                throw IOException("gagal menghapus data Vendor. HTTP kode: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getVendorById(idVendor: Int): Vendor {
        return vendorApiService.getVendorById(idVendor).data
    }

    override suspend fun getVendorByIdKlien(idKlien: Int): Vendor {
        return vendorApiService.getVendorByIdKlien(idKlien).data
    }
}