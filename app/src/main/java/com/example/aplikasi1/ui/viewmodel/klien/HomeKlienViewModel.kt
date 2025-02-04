package com.example.aplikasi1.ui.viewmodel.klien

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Klien
import com.example.aplikasi1.data.repository.KlienRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class KlienUiState {
    data class Success(val klien: List<Klien>) : KlienUiState()
    object Error : KlienUiState()
    object Loading : KlienUiState()
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeViewModelKlien(private val klienRepository: KlienRepository) : ViewModel() {
    var klienUiState: KlienUiState by mutableStateOf(KlienUiState.Loading)
        private set

    init {
        getKlien()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getKlien() {
        viewModelScope.launch {
            klienUiState = KlienUiState.Loading
            klienUiState = try {
                KlienUiState.Success(klienRepository.getKlien())
            } catch (e: IOException) {
                KlienUiState.Error
            } catch (e: HttpException) {
                KlienUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteKlien(idKlien: String) {
        viewModelScope.launch {
            try {
                klienRepository.deleteKlien(idKlien)
                getKlien() // Refresh data setelah penghapusan
            } catch (e: IOException) {
                klienUiState = KlienUiState.Error
            } catch (e: HttpException) {
                klienUiState = KlienUiState.Error
            }
        }
    }
}
