package com.example.aplikasi1.ui.viewmodel.acara

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Acara
import com.example.aplikasi1.data.model.Klien
import com.example.aplikasi1.data.model.Lokasi
import com.example.aplikasi1.data.repository.AcaraRepository
import com.example.aplikasi1.data.repository.KlienRepository
import com.example.aplikasi1.data.repository.LokasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UpdateUiEvent(
    val idAcara: String,
    val namaAcara: String,
    val deskripsiAcara: String,
    val tanggalMulai: String,
    val tanggalBerakhir: String,
    val idLokasi: String,
    val idKlien: String,
    val statusAcara: String,
)

sealed class UpdateUiState {
    object Idle : UpdateUiState()
    object Loading : UpdateUiState()
    data class Success(val acara: Acara) : UpdateUiState()
    data class Error(val message: String) : UpdateUiState()
}

class UpdateAcrViewModel(
    private val acaraRepository: AcaraRepository,
    private val klienRepo: KlienRepository,
    private val lokasiRepo: LokasiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UpdateUiState>(UpdateUiState.Idle)
    val uiState: StateFlow<UpdateUiState> = _uiState

    private var currentFormData: UpdateUiEvent? = null

    var klienList: List<Klien> = emptyList()
    var lokasiList: List<Lokasi> = emptyList()

    var klienIds: List<String> = emptyList()
    var lokasiIds: List<String> = emptyList()

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

    fun loadAcaraData(id: String) {
        _uiState.value = UpdateUiState.Loading
        viewModelScope.launch {
            try {
                val acara = acaraRepository.getAcarabyId(id)
                currentFormData = UpdateUiEvent(
                    idAcara = acara.idAcara,
                    namaAcara = acara.namaAcara,
                    deskripsiAcara = acara.deskripsiAcara,
                    tanggalMulai = acara.tanggalMulai,
                    tanggalBerakhir = acara.tanggalBerakhir,
                    idLokasi = acara.idLokasi,
                    idKlien = acara.idKlien,
                    statusAcara = acara.statusAcara
                )
                _uiState.value = UpdateUiState.Success(acara)
            } catch (e: Exception) {
                _uiState.value = UpdateUiState.Error("Failed to load acara data: ${e.message}")
            }
        }
    }

    fun updateUiState(event: UpdateUiEvent) {
        currentFormData = event
    }

    fun updateAcara() {
        val formData = currentFormData
        if (formData == null) {
            _uiState.value = UpdateUiState.Error("Form data is not initialized")
            return
        }

        _uiState.value = UpdateUiState.Loading
        viewModelScope.launch {
            try {
                val acara = Acara(
                    idAcara = formData.idAcara,
                    namaAcara = formData.namaAcara,
                    deskripsiAcara = formData.deskripsiAcara,
                    tanggalMulai = formData.tanggalMulai,
                    tanggalBerakhir = formData.tanggalBerakhir,
                    idLokasi = formData.idLokasi,
                    idKlien = formData.idKlien,
                    statusAcara = formData.statusAcara
                )
                acaraRepository.updateAcara(formData.idAcara, acara)
                _uiState.value = UpdateUiState.Success(acara)
            } catch (e: Exception) {
                _uiState.value = UpdateUiState.Error("Failed to update acara: ${e.message}")
            }
        }
    }
}