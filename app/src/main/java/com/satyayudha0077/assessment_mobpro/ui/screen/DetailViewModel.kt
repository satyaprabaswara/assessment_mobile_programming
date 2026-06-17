package com.satyayudha0077.assessment_mobpro.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyayudha0077.assessment_mobpro.database.BuahDao
import com.satyayudha0077.assessment_mobpro.model.Buah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val dao: BuahDao) : ViewModel() {

    suspend fun getBuah(id: Long): Buah? {
        return withContext(Dispatchers.IO) {
            dao.getBuahById(id)
        }
    }

    fun insert(nama: String, manfaat: String, imageResId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val buah = Buah(
                nama = nama,
                manfaat = manfaat,
                imageResId = imageResId
            )
            dao.insert(buah)
        }
    }

    fun update(id: Long, nama: String, manfaat: String, imageResId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val buah = Buah(
                id = id,
                nama = nama,
                manfaat = manfaat,
                imageResId = imageResId
            )
            dao.update(buah)
        }
    }

    fun deletePermanent(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deletePermanent(id)
        }
    }
}