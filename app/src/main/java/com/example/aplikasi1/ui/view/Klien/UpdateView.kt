package com.example.aplikasi1.ui.view.Klien

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasi1.ui.cw.CostumeTopAppBar
import com.example.aplikasi1.ui.navigasi.DestinasiNavigasi
import com.example.aplikasi1.ui.viewmodel.PenyediaViewModel
import com.example.aplikasi1.ui.viewmodel.klien.UpdateUiEvent
import com.example.aplikasi1.ui.viewmodel.klien.UpdateUiState
import com.example.aplikasi1.ui.viewmodel.klien.UpdateViewModelKlien


object DestinasiUpdateKlien : DestinasiNavigasi {
    override val route = "update/{idKlien}"
    override val titleRes = "Update Klien"
}
@OptIn(ExperimentalMaterial3Api::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun UpdateScreenKlien(
    idKlien: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateViewModelKlien = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsState()


    LaunchedEffect(idKlien) {
        viewModel.loadKlienData(idKlien)
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateKlien.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (uiState) {
                is UpdateUiState.Loading -> CircularProgressIndicator()
                is UpdateUiState.Success -> {
                    val klien = (uiState as UpdateUiState.Success).klien
                    UpdateForm(
                        idKlien = klien.idKlien.toString(),
                        namaKlien = klien.namaKlien,
                        kontakKlien = klien.kontakKlien,
                        onUpdateClick = {
                            viewModel.updateUiState(it)
                            viewModel.updateKlien()
                            onNavigateBack()
                        }
                    )
                }
                is UpdateUiState.Error -> {
                    Text("Error: ${(uiState as UpdateUiState.Error).message}")
                }
                else -> {}
            }
        }
    }
}

@Composable
fun UpdateForm(
    idKlien: String,
    namaKlien: String,
    kontakKlien: String,
    onUpdateClick: (UpdateUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var idKlien by remember { mutableStateOf(idKlien) }
    var namaKlien by remember { mutableStateOf(namaKlien) }
    var kontakKlien by remember { mutableStateOf(kontakKlien) }
    var selectedKontakType by remember { mutableStateOf("Email") }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = idKlien,
            onValueChange = { idKlien= it },
            label = { Text("IdKlien") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = namaKlien,
            onValueChange = { namaKlien= it },
            label = { Text("Nama Klien") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Text("Pilih Jenis Kontak:")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RadioButton(
                selected = selectedKontakType == "Email",
                onClick = { selectedKontakType = "Email" }
            )
            Text("Email")

            RadioButton(
                selected = selectedKontakType == "Nomor Telepon",
                onClick = { selectedKontakType = "Nomor Telepon" }
            )
            Text("Nomor Telepon")
        }

        Button(
            onClick = {
                onUpdateClick(
                    UpdateUiEvent(
                        idKlien = idKlien,
                        namaKlien = namaKlien,
                        kontakKlien = kontakKlien,
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update")
        }
    }
}