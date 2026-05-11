package com.satyayudha0077.assessment_mobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satyayudha0077.assessment_mobpro.R
import com.satyayudha0077.assessment_mobpro.ui.theme.Assessment_mobproTheme
import com.satyayudha0077.assessment_mobpro.util.ViewModelFactory

val daftarGambar = listOf(
    R.drawable.apel,
    R.drawable.alpukat,
    R.drawable.anggur,
    R.drawable.durian,
    R.drawable.jambu,
    R.drawable.jeruk,
    R.drawable.mangga,
    R.drawable.manggis,
    R.drawable.melon,
    R.drawable.naga,
    R.drawable.pepaya,
    R.drawable.pisang,
    R.drawable.strawberry
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {

    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var nama by remember { mutableStateOf("") }
    var manfaat by remember { mutableStateOf("") }
    var imageResId by remember {
        mutableStateOf(daftarGambar[0])
    }

    LaunchedEffect(key1 = Unit) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getBuah(id) ?: return@LaunchedEffect
        nama = data.nama
        manfaat = data.manfaat
        imageResId = data.imageResId
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali)
                        )
                    }
                },
                title = {
                    if (id == null) {
                        Text(text = stringResource(R.string.tambah))
                    } else {
                        Text(text = stringResource(R.string.edit))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            if (nama.isBlank() || manfaat.isBlank()) {
                                Toast.makeText(
                                    context,
                                    R.string.invalid,
                                    Toast.LENGTH_LONG
                                ).show()
                                return@IconButton
                            }
                            if (id == null) {
                                viewModel.insert(
                                    nama = nama,
                                    manfaat = manfaat,
                                    imageResId = imageResId
                                )

                            } else {
                                viewModel.update(
                                    id = id,
                                    nama = nama,
                                    manfaat = manfaat,
                                    imageResId = imageResId
                                )
                            }
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        FormBuah(
            imageResId = imageResId,
            onImageChange = { imageResId = it },
            title = nama,
            onTitleChange = { nama = it },
            desc = manfaat,
            onDescChange = { manfaat = it },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormBuah(
    imageResId: Int,
    onImageChange: (Int) -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    desc: String,
    onDescChange: (String) -> Unit,
    modifier: Modifier
) {

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (imageResId != 0) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "Pilih Gambar Buah",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(daftarGambar) { gambar ->

                Image(
                    painter = painterResource(id = gambar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable {
                            onImageChange(gambar)
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }
        OutlinedTextField(
            value = title,
            onValueChange = {
                onTitleChange(it)
            },
            label = {
                Text(text = stringResource(R.string.nama))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = desc,
            onValueChange = {
                onDescChange(it)
            },
            label = {
                Text(text = stringResource(R.string.manfaat))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    Assessment_mobproTheme {
        DetailScreen(rememberNavController())
    }
}