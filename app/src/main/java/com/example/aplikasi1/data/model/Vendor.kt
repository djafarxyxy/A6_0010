package com.example.aplikasi1.data.model

@Serializable
data class Vendor(
    @SerialName("id_vendor")
    val idVendor: Int, // ID unik vendor

    @SerialName("nama_vendor")
    val namaVendor: String, // Nama vendor

    @SerialName("kontak_vendor")
    val kontakVendor: String, // Informasi kontak vendor

    @SerialName("alamat_vendor")
    val alamatVendor: String, // Alamat lengkap vendor

    @SerialName("status_vendor")
    val statusVendor: String // Status vendor (aktif/non-aktif)
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