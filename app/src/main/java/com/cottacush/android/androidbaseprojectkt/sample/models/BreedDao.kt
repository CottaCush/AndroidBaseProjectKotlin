package com.cottacush.android.androidbaseprojectkt.sample.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDao {

    @Query("select *from databasebreedModel")
    fun getAllBreed() : LiveData<List<DatabaseBreedModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBreed(vararg breeds: DatabaseBreedModel)
}



