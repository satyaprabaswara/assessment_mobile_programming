package com.satyayudha0077.assessment_mobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyayudha0077.assessment_mobpro.R
import com.satyayudha0077.assessment_mobpro.model.Buah
import com.satyayudha0077.assessment_mobpro.ui.theme.Assessment_mobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val data = listOf(
        Buah("Alpukat", R.drawable.alpukat),
        Buah("Apel", R.drawable.apel),
        Buah("Jambu", R.drawable.jambu),
        Buah("Jeruk", R.drawable.jeruk),
        Buah("Pisang", R.drawable.pisang),
    )

    var index by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(data[index], Modifier.padding(innerPadding)) {
            index = if (index == data.size-1) 0 else index + 1
        }
    }
}

@Composable
fun ScreenContent(
    buah: Buah,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    var jumlah by remember { mutableStateOf("") }

    var berat by remember { mutableStateOf("") }

    val radioOptions = listOf(
        stringResource(R.string.satuan),
        stringResource(R.string.kilogram)
    )
    var jenis by remember {
        mutableStateOf(radioOptions[0])
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = buah.imageResId),
            contentDescription = stringResource(
                R.string.gambar,
                buah.nama
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = buah.nama,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 12.dp)
        )
        OutlinedTextField(
            value = jumlah,
            onValueChange = {
                jumlah = it
            },
            label = {
                Text(text = stringResource(R.string.jumlah))
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 12.dp)
        )
        OutlinedTextField(
            value = berat,
            onValueChange = {
                berat = it
            },
            label = {
                Text(text = stringResource(R.string.berat))
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 12.dp)
        )
        Row (
            modifier = Modifier
                .padding(top = 16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            radioOptions.forEach { text ->
                FruitOption(
                    label = text,
                    isSelected = jenis == text,
                    modifier = Modifier
                        .selectable(
                            selected = jenis == text,
                            onClick = {
                                jenis = text
                            },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    jumlah = ""
                    berat = ""
                    jenis = radioOptions[0]
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(50.dp),
                contentPadding = PaddingValues(
                    vertical = 14.dp
                )
            ) {
                Text(text = stringResource(R.string.ganti))
            }
            Button(
                onClick = {
                    onClick()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(50.dp),
                contentPadding = PaddingValues(
                    vertical = 14.dp
                )
            ) {
                Text(text = stringResource(R.string.count))
            }
        }
    }
}

@Composable
fun FruitOption(
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Assessment_mobproTheme {
        MainScreen()
    }
}