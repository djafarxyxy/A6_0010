package com.example.aplikasi1.data.model

@Serializable
data class Acara(
    @SerialName("id_acara")
    val idAcara: Int, // ID unik untuk acara

    @SerialName("nama_acara")
    val namaAcara: String, // Nama acara

    @SerialName("deskripsi_acara")
    val deskripsiAcara: String, // Deskripsi lengkap acara

    @SerialName("tanggal_mulai")
    val tanggalMulai: String, // Tanggal dan waktu mulai acara (format ISO 8601)

    @SerialName("tanggal_berakhir")
    val tanggalBerakhir: String, // Tanggal dan waktu berakhir acara (format ISO 8601)

    @SerialName("id_lokasi")
    val idLokasi: Int, // ID lokasi acara

    @SerialName("id_klien")
    val idKlien: Int, // ID klien acara

    @SerialName("status_acara")
    val statusAcara: String // Status acara (e.g., "Direncanakan", "Berlangsung", "Selesai")
)

@Serializable
data class BangunanResponse(
    val status: Boolean,
    val message: String,
    val data: List<Acara>
)

@Serializable
data class BangunanDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Acara
)