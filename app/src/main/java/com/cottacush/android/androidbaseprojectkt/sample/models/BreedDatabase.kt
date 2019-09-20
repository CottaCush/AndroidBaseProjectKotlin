package com.cottacush.android.androidbaseprojectkt.sample.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseBreedModel::class], version = 1)
abstract class BreedDatabase : RoomDatabase() {
    abstract val breedDao: BreedDao
}