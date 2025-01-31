package com.example.aplikasi1.ui.viewmodel.klien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Klien
import com.example.aplikasi1.data.repository.KlienRepository
import kotlinx.coroutines.launch

class InsertViewModelKlien(private val klienRepository: KlienRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertKlienState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertKlien() {
        viewModelScope.launch {
            try {
                klienRepository.insertKlien(uiState.insertUiEvent.toKlien())
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
    val idKlien: String = "",
    val namaklien: String = "",
    val kontakklien: String= "",
)

fun InsertUiEvent.toKlien(): Klien = Klien(
    idKlien = idKlien,
    namaKlien = namaklien,
    kontakKlien = kontakklien
)

fun Klien.toUiStateKlien(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Klien.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idKlien = idKlien,
    namaklien = namaKlien,
    kontakklien = kontakKlien

)
