package com.example.aplikasi1.ui.viewmodel.acara

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Acara
import com.example.aplikasi1.data.repository.AcaraRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState {
    data class Success(val acara: List<Acara>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeAcraViewModel(private val acaraRepository: AcaraRepository) : ViewModel() {
    var acaraUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getAcara()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getAcara() {
        viewModelScope.launch {
            acaraUiState = HomeUiState.Loading
            acaraUiState = try {
                HomeUiState.Success(acaraRepository.getAcara())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteAcara(id: String) {
        viewModelScope.launch {
            try {
                acaraRepository.deleteAcara(id)
            } catch (e: IOException) {
                acaraUiState = HomeUiState.Error
            } catch (e: HttpException) {
                acaraUiState = HomeUiState.Error
            }
        }
    }
}