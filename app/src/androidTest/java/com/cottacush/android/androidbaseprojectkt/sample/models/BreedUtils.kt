package com.cottacush.android.androidbaseprojectkt.sample.models

import androidx.room.PrimaryKey
import com.cottacush.android.androidbaseprojectkt.sample.Weight

class BreedUtils {

    fun createBreedTestUtils() : DatabaseBreedModel {
        val weight =  WeightConverter().toWeight("40")
        return DatabaseBreedModel(
           "1" ,"fur cat", "2 years", "cat woman", "gotham", 3, 4, "", weight, "wiki.com/catwoman")
    }
}