package com.satyayudha0077.assessment_mobpro.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buah")
data class Buah(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val imageResId: Int,
    val manfaat: String
)