package com.example.aplikasi1.ui.acara

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasi1.ui.cw.CostumeTopAppBar
import com.example.aplikasi1.ui.navigasi.DestinasiNavigasi
import com.example.aplikasi1.ui.viewmodel.PenyediaViewModel
import com.example.aplikasi1.ui.viewmodel.acara.InsertAcrViewModelViewModel
import com.example.aplikasi1.ui.viewmodel.acara.InsertUiEvent
import com.example.aplikasi1.ui.viewmodel.acara.InsertUiState
import com.example.aplikasi1.ui.viewmodel.widget.SelectedTextField
import kotlinx.coroutines.launch

object DestinasiEntryAcara : DestinasiNavigasi {
    override val route = "insert_acara"
    override val titleRes = "Entry Acara"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryAcaraScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertAcrViewModelViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryAcara.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onAcaraValueChange = viewModel::updateInsertAcaraState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertAcara()
                    navigateBack()
                }
            },
            viewModel = viewModel,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun EntryBody(
    insertUiState: InsertUiState,
    onAcaraValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    viewModel: InsertAcrViewModelViewModel,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            viewModel = viewModel,
            onValueChange = onAcaraValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    viewModel: InsertAcrViewModelViewModel,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true,
) {
    val statusAcaraOption = listOf("Direncakan", "Berlangsung", "Selesai")

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.idAcara,
            onValueChange = { onValueChange(insertUiEvent.copy(idAcara = it)) },
            label = { Text("Id Acara") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.namaAcara,
            onValueChange = { onValueChange(insertUiEvent.copy(namaAcara = it)) },
            label = { Text("Nama Acara") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsiAcara,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsiAcara = it)) },
            label = { Text("Deskripsi Acara") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.tanggalMulai,
            onValueChange = { onValueChange(insertUiEvent.copy(tanggalMulai = it)) },
            label = { Text("Tanggal Mulai") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.tanggalBerakhir,
            onValueChange = { onValueChange(insertUiEvent.copy(tanggalBerakhir = it)) },
            label = { Text("Tanggal Berakhir") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )


        SelectedTextField(
            selectedValue = insertUiEvent.idKlien,
            options = viewModel.klienIds,
            label = "Pilih ID Klien",
            onValueChangedEvent = {
                onValueChange(insertUiEvent.copy(idKlien = it))
            },
            modifier = Modifier.fillMaxWidth()
        )

        SelectedTextField(
            selectedValue = insertUiEvent.idLokasi,
            options = viewModel.lokasiIds,
            label = "Pilih ID Lokasi",
            onValueChangedEvent = {
                onValueChange(insertUiEvent.copy(idLokasi = it))
            },
            modifier = Modifier.fillMaxWidth()
        )

    }
    Text(text = "Status Acara", style = MaterialTheme.typography.bodyMedium)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        statusAcaraOption.forEach { status ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 1.dp)
            ) {
                RadioButton(
                    selected = insertUiEvent.statusAcara == status,
                    onClick = { onValueChange(insertUiEvent.copy(statusAcara = status)) }
                )
                Text(text = status)
            }
        }
    }

    Divider(
        thickness = 8.dp,
        modifier = Modifier.padding(12.dp)
    )
}
