package com.example.aplikasi1.ui.viewmodel.lokasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Lokasi
import com.example.aplikasi1.data.repository.LokasiRepository
import kotlinx.coroutines.launch

class InsertViewModelLokasi(private val lokasiRepository: LokasiRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertLokasiState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertLokasi() {
        viewModelScope.launch {
            try {
                lokasiRepository.insertLokasi(uiState.insertUiEvent.toLokasi())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idLokasi: String = "",
    val namaLokasi: String = "",
    val alamatLokasi: String = "",
    val kapasitas: String = ""
)

fun InsertUiEvent.toLokasi(): Lokasi = Lokasi(
    idLokasi = idLokasi,
    namaLokasi = namaLokasi,
    alamatLokasi = alamatLokasi,
    kapasitas = kapasitas
)

fun Lokasi.toUiStateLokasi(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Lokasi.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idLokasi = idLokasi,
    namaLokasi = namaLokasi,
    alamatLokasi = alamatLokasi,
    kapasitas = kapasitas
)
