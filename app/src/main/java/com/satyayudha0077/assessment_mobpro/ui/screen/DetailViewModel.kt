package com.satyayudha0077.assessment_mobpro.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyayudha0077.assessment_mobpro.database.BuahDao
import com.satyayudha0077.assessment_mobpro.model.Buah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: BuahDao) : ViewModel() {

    fun insert(nama: String, manfaat: String, imageResId: Int) {

        val buah = Buah(
            nama = nama,
            manfaat = manfaat,
            imageResId = imageResId
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(buah)
        }
    }

    suspend fun getBuah(id: Long): Buah? {
        return dao.getBuahById(id)
    }
}