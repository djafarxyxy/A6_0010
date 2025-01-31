package com.example.aplikasi1.ui.viewmodel.acara

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.aplikasi1.data.model.Acara
import com.example.aplikasi1.data.model.AcaraResponse
import com.example.aplikasi1.data.repository.AcaraRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState {
    data class Success(val acara: List<Acara>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeAcraViewModel(private val acaraRepository: AcaraRepository) : ViewModel() {
    var acaraUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getAcara()
    }

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