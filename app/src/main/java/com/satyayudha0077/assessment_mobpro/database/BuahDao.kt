package com.satyayudha0077.assessment_mobpro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.satyayudha0077.assessment_mobpro.model.Buah
import kotlinx.coroutines.flow.Flow

@Dao
interface BuahDao {

    @Insert
    suspend fun insert(buah: Buah)

    @Update
    suspend fun update(buah: Buah)

    @Query("SELECT * FROM buah ORDER BY manfaat DESC")
    fun getBuah(): Flow<List<Buah>>
}