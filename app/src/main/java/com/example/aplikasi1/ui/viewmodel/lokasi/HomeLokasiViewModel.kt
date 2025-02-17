package com.example.aplikasi1.ui.viewmodel.lokasi

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Lokasi
import com.example.aplikasi1.data.repository.LokasiRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class LokasiUiState {
    data class Success(val lokasi: List<Lokasi>) : LokasiUiState()
    object Error : LokasiUiState()
    object Loading : LokasiUiState()
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeViewModelLokasi(private val lokasiRepo: LokasiRepository) : ViewModel() {
    var lokasiUiState: LokasiUiState by mutableStateOf(LokasiUiState.Loading)
        private set

    init {
        getLokasi()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getLokasi() {
        viewModelScope.launch {
            lokasiUiState = LokasiUiState.Loading
            lokasiUiState = try {
                LokasiUiState.Success(lokasiRepo.getLokasi())
            } catch (e: IOException) {
                LokasiUiState.Error
            } catch (e: HttpException) {
                LokasiUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteLokasi(idLokasi: String) {
        viewModelScope.launch {
            try {
                lokasiRepo.deleteLokasi(idLokasi)
                getLokasi()
            } catch (e: IOException) {
                lokasiUiState = LokasiUiState.Error
            } catch (e: HttpException) {
                lokasiUiState = LokasiUiState.Error
            }
        }
    }
}