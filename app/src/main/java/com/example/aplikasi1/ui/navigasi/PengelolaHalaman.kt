package com.example.aplikasi1.ui.navigasi

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplikasi1.ui.acara.DestinasiDetailAcara
import com.example.aplikasi1.ui.acara.DestinasiEntryAcara
import com.example.aplikasi1.ui.acara.DestinasiHomeAcara
import com.example.aplikasi1.ui.acara.DetailViewAcara
import com.example.aplikasi1.ui.acara.EntryAcaraScreen
import com.example.aplikasi1.ui.acara.HomeScreenAcara
import com.example.aplikasi1.ui.view.Acara.DestinasiUpdateAcara
import com.example.aplikasi1.ui.view.Acara.UpdateScreenAcara
import com.example.aplikasi1.ui.view.Klien.DestinasiDetailKlien
import com.example.aplikasi1.ui.view.Klien.DestinasiEntryKlien
import com.example.aplikasi1.ui.view.Klien.DestinasiHomeKlien
import com.example.aplikasi1.ui.view.Klien.DestinasiUpdateKlien
import com.example.aplikasi1.ui.view.Klien.DetailViewKlien
import com.example.aplikasi1.ui.view.Klien.EntryKlienScreen
import com.example.aplikasi1.ui.view.Klien.HomeScreenKlien
import com.example.aplikasi1.ui.view.Klien.UpdateScreenKlien
import com.example.aplikasi1.ui.view.lokasi.DestinasiDetailLokasi
import com.example.aplikasi1.ui.view.lokasi.DestinasiEntryLokasi
import com.example.aplikasi1.ui.view.lokasi.DestinasiHomeLokasi
import com.example.aplikasi1.ui.view.lokasi.DestinasiUpdateLokasi
import com.example.aplikasi1.ui.view.lokasi.DetailViewLokasi
import com.example.aplikasi1.ui.view.lokasi.EntryLokasiScreen
import com.example.aplikasi1.ui.view.lokasi.HomeLokasi
import com.example.aplikasi1.ui.view.lokasi.UpdateScreenLokasi
import com.example.aplikasi1.ui.view.vendor.DestinasiDetailVendor
import com.example.aplikasi1.ui.view.vendor.DestinasiEntryVendor
import com.example.aplikasi1.ui.view.vendor.DestinasiHomeVendor
import com.example.aplikasi1.ui.view.vendor.DestinasiUpdateVendor
import com.example.aplikasi1.ui.view.vendor.DetailViewVendor
import com.example.aplikasi1.ui.view.vendor.EntryVendorScreen
import com.example.aplikasi1.ui.view.vendor.HomeScreenVendor
import com.example.aplikasi1.ui.view.vendor.UpdateScreenVendor


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeAcara.route,  // Ubah start destination
        modifier = Modifier,
    ) {
        // ACARA
        composable(DestinasiHomeAcara.route) {
            HomeScreenAcara(
                navigateToItemEntry = { navController.navigate(DestinasiEntryAcara.route) },
                navigateToKlien = { navController.navigate(DestinasiHomeKlien.route)},
                navigateToLokasi = { navController.navigate(DestinasiHomeLokasi.route)},
                navigateToVendor = { navController.navigate(DestinasiHomeVendor.route) },
                onDetailClick = { idAcara ->
                    navController.navigate("${DestinasiDetailAcara.route}/$idAcara")
                }
            )
        }

        // Entry Acara Screen
        composable(DestinasiEntryAcara.route) {
            EntryAcaraScreen(
                navigateBack = { navController.navigateUp() },
            )
        }

        // Detail Acara
        composable(
            route = "${DestinasiDetailAcara.route}/{idAcara}",
            arguments = listOf(navArgument("idAcara") { type = NavType.StringType })
        ) { backStackEntry ->
            val idAcara = backStackEntry.arguments?.getString("idAcara") ?: ""
            DetailViewAcara(
                idAcara = idAcara,
                onNavigateBack = { navController.navigateUp() },
                onEditClick = { idAcara ->
                    navController.navigate("${DestinasiUpdateAcara.route}/$idAcara")
                },
            )
        }

        // Edit Acara
        composable(
            route = "${DestinasiUpdateAcara.route}/{idAcara}",
            arguments = listOf(navArgument("idAcara") { type = NavType.StringType })
        ) { backStackEntry ->
            val idAcara = backStackEntry.arguments?.getString("idAcara") ?: ""
            UpdateScreenAcara(
                idAcara = idAcara,
                onNavigateBack = { navController.navigateUp() }
            )
        }

        // LOKASI HOME
        composable(DestinasiHomeLokasi.route) {
            HomeLokasi(
                navigateToLokasiEntry = { navController.navigate(DestinasiEntryLokasi.route) },
                onDetailClick = { idLokasi ->
                    navController.navigate("${DestinasiDetailLokasi.route}/$idLokasi")
                },
                navigateBack = { navController.navigate(DestinasiHomeAcara.route) }
            )
        }

        // Entry Lokasi Screen
        composable(DestinasiEntryLokasi.route) {
            EntryLokasiScreen(
                navigateBack = { navController.navigateUp() },
            )
        }

        // Detail Lokasi
        composable(
            route = "${DestinasiDetailLokasi.route}/{idLokasi}",
            arguments = listOf(navArgument("idLokasi") { type = NavType.StringType })
        ) { backStackEntry ->
            val idLokasi = backStackEntry.arguments?.getString("idLokasi") ?: ""
            DetailViewLokasi(
                idLokasi = idLokasi,
                onNavigateBack = { navController.navigateUp() },
                onEditClick = { idLokasi ->
                    navController.navigate("${DestinasiUpdateLokasi.route}/$idLokasi")
                },
            )
        }

        // Edit Lokasi
        composable(
            route = "${DestinasiUpdateLokasi.route}/{idLokasi}",
            arguments = listOf(navArgument("idLokasi") { type = NavType.StringType })
        ) { backStackEntry ->
            val idLokasi = backStackEntry.arguments?.getString("idLokasi") ?: ""
            UpdateScreenLokasi(
                idLokasi = idLokasi,
                onNavigateBack = { navController.navigateUp() }
            )
        }

        // KLIEN HOME
        composable(DestinasiHomeKlien.route) {
            HomeScreenKlien(
                navigateToKlienEntry = { navController.navigate(DestinasiEntryKlien.route) },
                onDetailClick = { idKlien ->
                    navController.navigate("${DestinasiDetailKlien.route}/$idKlien")
                },
                navigateBack = { navController.navigate(DestinasiHomeAcara.route) }
            )
        }

        // Entry Klien Screen
        composable(DestinasiEntryKlien.route) {
            EntryKlienScreen(
                navigateBack = { navController.navigateUp() },
            )
        }

        // Detail Klient
        composable(
            route = "${DestinasiDetailKlien.route}/{idKlien}",
            arguments = listOf(navArgument("idKlien") { type = NavType.StringType })
        ) { backStackEntry ->
            val idKlien = backStackEntry.arguments?.getString("idKlien") ?: ""
            DetailViewKlien(
                idKlien = idKlien,
                onNavigateBack = { navController.navigateUp() },
                onEditClick = { idLokasi ->
                    navController.navigate("${DestinasiUpdateKlien.route}/$idKlien")
                },
            )
        }

        // Edit Klien
        composable(
            route = "${DestinasiUpdateKlien.route}/{idKlien}",
            arguments = listOf(navArgument("idKlien") { type = NavType.StringType })
        ) { backStackEntry ->
            val idKlien = backStackEntry.arguments?.getString("idKlien") ?: ""
            UpdateScreenKlien(
                idKlien = idKlien,
                onNavigateBack = { navController.navigateUp() }
            )
        }

        // VENDORS HOME
        composable(DestinasiHomeVendor.route) {
            HomeScreenVendor(
                navigateToVendorEntry = { navController.navigate(DestinasiEntryVendor.route) },
                onDetailClick = { idVendor ->
                    navController.navigate("${DestinasiDetailVendor.route}/$idVendor")
                },
                navigateBack = { navController.navigate(DestinasiHomeAcara.route) }
            )
        }

        // Entry Vendor Screen
        composable(DestinasiEntryVendor.route) {
            EntryVendorScreen(
                navigateBack = { navController.navigateUp() },
            )
        }

        // Detail Vendor
        composable(
            route = "${DestinasiDetailVendor.route}/{idVendor}",
            arguments = listOf(navArgument("idVendor") { type = NavType.StringType })
        ) { backStackEntry ->
            val idVendor = backStackEntry.arguments?.getString("idVendor") ?: ""
            DetailViewVendor(
                idVendor = idVendor,
                onNavigateBack = { navController.navigateUp() },
                onEditClick = { idVendor ->
                    navController.navigate("${DestinasiUpdateVendor.route}/$idVendor")
                },
            )
        }

        // Edit Vendor
        composable(
            route = "${DestinasiUpdateVendor.route}/{idVendor}",
            arguments = listOf(navArgument("idVendor") { type = NavType.StringType })
        ) { backStackEntry ->
            val idVendor = backStackEntry.arguments?.getString("idVendor") ?: ""
            UpdateScreenVendor(
                idVendor = idVendor,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
