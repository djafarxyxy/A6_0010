package com.example.aplikasi1.ui.viewmodel.acara

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Acara
import com.example.aplikasi1.data.model.Klien
import com.example.aplikasi1.data.model.Lokasi
import com.example.aplikasi1.data.repository.AcaraRepository
import com.example.aplikasi1.data.repository.KlienRepository
import com.example.aplikasi1.data.repository.LokasiRepository
import kotlinx.coroutines.launch

class InsertAcrViewModelViewModel(
    private val acaraRepo: AcaraRepository,
    private val klienRepo: KlienRepository,
    private val lokasiRepo: LokasiRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    var klienList by mutableStateOf<List<Klien>>(emptyList())
        private set

    var lokasiList by mutableStateOf<List<Lokasi>>(emptyList())
        private set

    var klienIds by mutableStateOf<List<String>>(emptyList())
        private set

    var lokasiIds by mutableStateOf<List<String>>(emptyList())
        private set

    init {
        fetchKlienList()
        fetchLokasiList()
    }

    private fun fetchKlienList() {
        viewModelScope.launch {
            try {
                klienList = klienRepo.getKlien()
                klienIds = klienList.map { it.idKlien }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchLokasiList() {
        viewModelScope.launch {
            try {
                lokasiList = lokasiRepo.getLokasi()
                lokasiIds = lokasiList.map { it.idLokasi }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertAcaraState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertAcara() {
        viewModelScope.launch {
            try {
                acaraRepo.insertAcara(uiState.insertUiEvent.toAcara())
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
    val idAcara: String = "",
    val namaAcara: String = "",
    val deskripsiAcara: String = "",
    val tanggalMulai: String = "",
    val tanggalBerakhir: String = "",
    val idLokasi: String = "",
    val idKlien: String = "",
    val statusAcara: String = ""
)

fun InsertUiEvent.toAcara(): Acara = Acara(
    idAcara = idAcara,
    namaAcara = namaAcara,
    deskripsiAcara = deskripsiAcara,
    tanggalMulai = tanggalMulai,
    tanggalBerakhir = tanggalBerakhir,
    idLokasi = idLokasi,
    idKlien = idKlien,
    statusAcara = statusAcara
)

fun Acara.toUiStateAcara(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Acara.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idAcara = idAcara,
    namaAcara = namaAcara,
    deskripsiAcara = deskripsiAcara,
    tanggalMulai = tanggalMulai,
    tanggalBerakhir = tanggalBerakhir,
    idLokasi = idLokasi,
    idKlien = idKlien,
    statusAcara = statusAcara

)