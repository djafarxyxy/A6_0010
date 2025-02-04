package com.example.aplikasi1.ui.viewmodel.vendor

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi1.data.model.Vendor
import com.example.aplikasi1.data.repository.VendorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState {
    data class Success(val vendors: List<Vendor>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeViewModelVendor(private val vendorRepository: VendorRepository) : ViewModel() {
    var vendorUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getVendors()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getVendors() {
        viewModelScope.launch {
            vendorUiState = HomeUiState.Loading
            vendorUiState = try {
                HomeUiState.Success(vendorRepository.getVendors())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteVendor(idVendor: String) {
        viewModelScope.launch {
            try {
                vendorRepository.deleteVendor(idVendor)
                // Refresh the list after deletion
                getVendors()
            } catch (e: IOException) {
                vendorUiState = HomeUiState.Error
            } catch (e: HttpException) {
                vendorUiState = HomeUiState.Error
            }
        }
    }
}
