package com.satyayudha0077.assessment_mobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun ScreenContent(buah: Buah, modifier: Modifier = Modifier, onClick:() -> Unit) {
    var jumlah by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = buah.imageResId),
            contentDescription = stringResource(R.string.gambar, buah.nama),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(132.dp),

        )
        Text(
            text = buah.nama,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 16.dp),
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
                .padding(top = 24.dp)
        )
        OutlinedTextField(
            value = jumlah,
            onValueChange = {
                jumlah = it
            },
            label = {
                Text(text = stringResource(R.string.kilogram))
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 24.dp)
        )
        Button(
            onClick = { onClick() },
            modifier = Modifier.fillMaxWidth(0.5f).padding(top = 24.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = stringResource(R.string.lanjut))
        }
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