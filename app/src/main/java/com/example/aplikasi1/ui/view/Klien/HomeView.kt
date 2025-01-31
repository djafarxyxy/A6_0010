package com.example.aplikasi1.ui.view.Klien

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasi1.R
import com.example.aplikasi1.data.model.Klien
import com.example.aplikasi1.ui.navigasi.DestinasiNavigasi
import com.example.aplikasi1.ui.viewmodel.PenyediaViewModel
import com.example.aplikasi1.ui.viewmodel.klien.HomeViewModelKlien
import com.example.aplikasi1.ui.viewmodel.klien.KlienUiState
import kotlinx.coroutines.flow.StateFlow

object DestinasiHomeKlien : DestinasiNavigasi {
    override val route = "home_klien"
    override val titleRes = "Daftar Klien"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenKlien (
    navigateToKlienEntry: () -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModelKlien = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = DestinasiHomeKlien.titleRes,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { viewModel.getKlien() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToKlienEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Klien")
            }
        },
    ) { innerPadding ->
        HomeStatus(
            klienUiState = viewModel.klienUiState,
            retryAction = { viewModel.getKlien() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteKlien(it.idKlien.toString())
                viewModel.getKlien()
            }
        )
    }
}

@Composable
fun HomeStatus(
    klienUiState: KlienUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Klien) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (klienUiState) {
        is KlienUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is KlienUiState.Success ->
            if (klienUiState.klien.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data klien")
                }
            } else {
                KlienLayout(
                    klien = klienUiState.klien,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idKlien.toString())
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is KlienUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun KlienLayout(
    klien: List<Klien>,
    modifier: Modifier = Modifier,
    onDetailClick: (Klien) -> Unit,
    onDeleteClick: (Klien) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(klien) { kontak ->
            KlienCard(
                klien = kontak,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(kontak) },
                onDeleteClick = {
                    onDeleteClick(kontak)
                }
            )
        }
    }
}

@Composable
fun KlienCard(
    klien: Klien,
    modifier: Modifier = Modifier,
    onDeleteClick: (Klien) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = klien.namaKlien,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(klien) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = klien.idKlien,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Text(
                text = klien.kontakKlien,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loding),
        contentDescription = stringResource(R.string.loding)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error), contentDescription = ""
        )
        Text(text = stringResource(R.string.Fail), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}
