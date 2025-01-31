package com.example.aplikasi1.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Klien(
    @SerialName("id_klien")
    val idKlien: String, // ID unik klien

    @SerialName("nama_klien")
    val namaKlien: String, // Nama klien

    @SerialName("kontak_klien")
    val kontakKlien: String // Informasi kontak klien (email, telepon)
)

data class KlienResponse(
    val status: Boolean, // Status respon (berhasil/gagal)
    val message: String, // Pesan respon
    val data: List<Klien> // Daftar data klien
)

@Serializable
data class KlienDetailResponse(
    val status: Boolean, // Status respon (berhasil/gagal)
    val message: String, // Pesan respon
    val data: Klien // Detail data klien
)
