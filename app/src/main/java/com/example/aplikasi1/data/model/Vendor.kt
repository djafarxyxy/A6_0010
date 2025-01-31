package com.example.aplikasi1.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vendor(
    @SerialName("id_vendor")
    val idVendor: String, // ID unik vendor

    @SerialName("nama_vendor")
    val namaVendor: String, // Nama vendor

    @SerialName("jenis_vendor")
    val jenisVendor: String, // jenis layanan vendor

    @SerialName("kontak_vendor")
    val kontakVendor: String, // Informasi kontak vendor
)

@Serializable
data class VendorResponse(
    val status: Boolean, // Status respon (berhasil/gagal)
    val message: String, // Pesan respon
    val data: List<Vendor> // Daftar data vendor
)

@Serializable
data class VendorDetailResponse(
    val status: Boolean, // Status respon (berhasil/gagal)
    val message: String, // Pesan respon
    val data: Vendor // Detail data vendor
)