package com.example.aplikasi1.ui.viewmodel.lokasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Lokasi
import com.example.aplikasi1.data.repository.LokasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val lokasi: Lokasi) : DetailUiState()
    object Error : DetailUiState()
}

class DetailViewModelLokasi(private val repository: LokasiRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getLokasiById(idLokasi: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val lokasi = repository.getLokasiById(idLokasi)
                if (lokasi != null) {
                    _uiState.value = DetailUiState.Success(lokasi)
                } else {
                    _uiState.value = DetailUiState.Error
                }
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error
            }
        }
    }
}
