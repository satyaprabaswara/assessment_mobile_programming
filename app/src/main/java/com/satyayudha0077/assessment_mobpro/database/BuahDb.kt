package com.satyayudha0077.assessment_mobpro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.satyayudha0077.assessment_mobpro.model.Buah

@Database(entities = [Buah::class], version = 2, exportSchema = false)
abstract class BuahDb : RoomDatabase() {

    abstract val dao: BuahDao

    companion object {
        @Volatile
        private var INSTANCE: BuahDb? = null

        fun getInstance(context: Context): BuahDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BuahDb::class.java,
                        "buah.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}