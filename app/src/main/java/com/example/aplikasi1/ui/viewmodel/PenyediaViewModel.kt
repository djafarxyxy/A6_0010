package com.example.aplikasi1.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.aplikasi1.AcaraApplications
import com.example.aplikasi1.ui.viewmodel.acara.DetailAcaraViewModel
import com.example.aplikasi1.ui.viewmodel.acara.HomeAcraViewModel
import com.example.aplikasi1.ui.viewmodel.acara.InsertAcrViewModelViewModel
import com.example.aplikasi1.ui.viewmodel.acara.UpdateAcrViewModel
import com.example.aplikasi1.ui.viewmodel.klien.DetailViewModelKlien
import com.example.aplikasi1.ui.viewmodel.klien.HomeViewModelKlien
import com.example.aplikasi1.ui.viewmodel.klien.InsertViewModelKlien
import com.example.aplikasi1.ui.viewmodel.klien.UpdateViewModelKlien
import com.example.aplikasi1.ui.viewmodel.lokasi.DetailViewModelLokasi
import com.example.aplikasi1.ui.viewmodel.lokasi.HomeViewModelLokasi
import com.example.aplikasi1.ui.viewmodel.lokasi.InsertViewModelLokasi
import com.example.aplikasi1.ui.viewmodel.lokasi.UpdateViewModellokasi
import com.example.aplikasi1.ui.viewmodel.vendor.DetailViewModelVendor
import com.example.aplikasi1.ui.viewmodel.vendor.HomeViewModelVendor
import com.example.aplikasi1.ui.viewmodel.vendor.InsertViewModelVendor
import com.example.aplikasi1.ui.viewmodel.vendor.UpdateViewModelVendor


object PenyediaViewModel {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    val Factory = viewModelFactory {
        // ------------------Bangunan------------------- //
        initializer {
            HomeAcraViewModel(AcaraApp().container.acaraRepository)
        }
        initializer {
            InsertAcrViewModelViewModel(
                AcaraApp().container.acaraRepository, AcaraApp().container.klienRepository,  AcaraApp().container.lokasiRepository )
        }
        initializer {
            DetailAcaraViewModel(
                AcaraApp().container.acaraRepository
            )
        }
        initializer {
            UpdateAcrViewModel(
                AcaraApp().container.acaraRepository, AcaraApp().container.klienRepository,  AcaraApp().container.lokasiRepository
            )
        }

        initializer {
            HomeViewModelKlien(AcaraApp().container.klienRepository)
        }
        initializer {
            InsertViewModelKlien(AcaraApp().container.klienRepository)
        }
        initializer {
            DetailViewModelKlien(
                AcaraApp().container.klienRepository
            )
        }
        initializer {
            UpdateViewModelKlien(
                AcaraApp().container.klienRepository
            )
        }
        // ------------------Mahasiswa------------------- //
        initializer {
            HomeViewModelLokasi(AcaraApp().container.lokasiRepository)
        }
        initializer {
            InsertViewModelLokasi(AcaraApp().container.lokasiRepository)
        }
        initializer {
            DetailViewModelLokasi(
                AcaraApp().container.lokasiRepository
            )
        }
        initializer {
            UpdateViewModellokasi(
                AcaraApp().container.lokasiRepository
            )
        }

        // ------------------Pembayaran Sewa------------------- //
        initializer {
            HomeViewModelVendor(AcaraApp().container.vendorRepository)
        }
        initializer {
            InsertViewModelVendor(AcaraApp().container.vendorRepository)
        }
        initializer {
            DetailViewModelVendor(
                AcaraApp().container.vendorRepository
            )
        }
        initializer {
            UpdateViewModelVendor(

                AcaraApp().container.vendorRepository
            )
        }
    }

}

fun CreationExtras.AcaraApp() : AcaraApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AcaraApplications)