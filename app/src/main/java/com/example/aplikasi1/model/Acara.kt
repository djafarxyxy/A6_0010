package com.example.aplikasi1.model

data class Acara(
    val id: String,
    val namaAcara: String,
    val tanggal: String,
    val lokasi: String,
    val deskripsi: String
) {
    constructor() : this("", "", "", "", "")
}