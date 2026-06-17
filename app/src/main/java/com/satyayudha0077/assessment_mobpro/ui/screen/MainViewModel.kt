package com.satyayudha0077.assessment_mobpro.ui.screen

import android.graphics.Bitmap
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyayudha0077.assessment_mobpro.model.Buah
import com.satyayudha0077.assessment_mobpro.network.ApiStatus
import com.satyayudha0077.assessment_mobpro.network.BuahApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class MainViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Buah>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun retrieveData(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = BuahApi.service.getFruits()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                status.value = ApiStatus.FAILED
            }
        }
    }

    fun saveData(
        userId: String,
        nama: String,
        manfaat: String,
        bitmap: Bitmap
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val uniqueId = System.currentTimeMillis()

                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

                val requestBody = stream.toByteArray()
                    .toRequestBody("image/png".toMediaType())

                val fileName = "buah_$uniqueId.png"

                BuahApi.service.uploadImage(
                    fileName = fileName,
                    body = requestBody
                )

                val buahBaru = Buah(
                    id = uniqueId,
                    nama = nama,
                    manfaat = manfaat,
                    imageResId =
                        "https://qhtiblzrsthedprjzcse.supabase.co/storage/v1/object/public/foto-buah/$fileName"
                )

                BuahApi.service.postBuah(buah = buahBaru)

                retrieveData(userId)

            } catch (e: Exception) {
                Log.e("UPLOAD_ERROR", "FULL ERROR", e)

                if (e is retrofit2.HttpException) {
                    Log.e(
                        "UPLOAD_ERROR",
                        "CODE=${e.code()} BODY=${e.response()?.errorBody()?.string()}"
                    )
                }

                errorMessage.value = e.toString()
            }
        }
    }

    fun deleteData(userId: String, id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                BuahApi.service.deleteBuah(id = "eq.$id")
                retrieveData(userId)
            } catch (e: Exception) {
                errorMessage.value = "Gagal menghapus: ${e.message}"
            }
        }
    }

    fun updateData(
        id: Long,
        nama: String,
        manfaat: String,
        imageUrl: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val buahUpdate = Buah(
                    id = id,
                    nama = nama,
                    manfaat = manfaat,
                    imageResId = imageUrl
                )

                BuahApi.service.updateBuah(
                    id = "eq.$id",
                    buah = buahUpdate
                )

                retrieveData("")

            } catch (e: Exception) {
                Log.e("UPDATE_ERROR", "FULL ERROR", e)
            }
        }
    }

    fun updateData(
        userId: String,
        buah: Buah
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                BuahApi.service.updateBuah(
                    id = "eq.${buah.id}",
                    buah = buah
                )

                retrieveData(userId)

            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun clearMessage() { errorMessage.value = null }
}