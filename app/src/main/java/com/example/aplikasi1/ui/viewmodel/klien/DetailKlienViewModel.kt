package com.example.aplikasi1.ui.viewmodel.klien

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Klien
import com.example.aplikasi1.data.repository.KlienRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val klien: Klien) : DetailUiState()
    object Error : DetailUiState()
}

class DetailViewModelKlien(private val repository: KlienRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getKlienById(idKlien: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val klien = repository.getKlienById(idKlien)
                if (klien != null) {
                    _uiState.value = DetailUiState.Success(klien)
                } else {
                    _uiState.value = DetailUiState.Error
                }
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error
            }
        }
    }
}

