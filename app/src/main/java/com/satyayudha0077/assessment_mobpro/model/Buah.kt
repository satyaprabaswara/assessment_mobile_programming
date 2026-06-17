package com.satyayudha0077.assessment_mobpro.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "buah")
data class Buah(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @Json(name = "nama")
    val nama: String,
    @Json(name = "imageResId")
    val imageResId: String = "",
    @Json(name = "manfaat")
    val manfaat: String = ""
)