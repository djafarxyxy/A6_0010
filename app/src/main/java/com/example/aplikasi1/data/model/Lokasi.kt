package com.example.aplikasi1.data.model

@Serializable
data class Lokasi(
    @SerialName("id_lokasi")
    val idLokasi: Int, // ID unik lokasi

    @SerialName("nama_lokasi")
    val namaLokasi: String, // Nama lokasi

    @SerialName("alamat_lokasi")
    val alamatLokasi: String, // Alamat lengkap lokasi

    val kapasitas: Int // Kapasitas maksimal lokasi
)

@Serializable
data class LokasiResponse(
    val status: Boolean, // Status respon (berhasil/gagal)
    val message: String, // Pesan respon
    val data: List<Lokasi> // Daftar data lokasi
)

@Serializable
data class LokasiDetailResponse(
    val status: Boolean, // Status respon (berhasil/gagal)
    val message: String, // Pesan respon
    val data: Lokasi // Detail data lokasi
)
