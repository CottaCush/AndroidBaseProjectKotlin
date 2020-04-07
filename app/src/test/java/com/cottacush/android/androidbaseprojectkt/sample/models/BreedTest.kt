package com.cottacush.android.androidbaseprojectkt.sample.models

import com.cottacush.android.androidbaseprojectkt.sample.Weight
import com.google.common.truth.Truth
import org.junit.Test

class BreedTest{

    lateinit var expectedBreedModel : Breed

    @Test
    fun isBreedModel_correct() {
        //Given
        expectedBreedModel = Breed(
            id = "id",
            description = "cat description",
            lifeSpan = "4 years",
            name = "cat name",
            origin = "nevada",
            socialNeeds = 0,
            strangerFriendly = 1,
            temperament = "angry",
            weight = Weight(imperial = "35", metric = "70"),
            wikipediaUrl = "www.imageurl.com"
        )
        val actualBreedModel = expectedBreedModel.copy()

        //Then
        Truth.assertThat(actualBreedModel).isInstanceOf(Breed::class.java)
        Truth.assertThat(expectedBreedModel).isInstanceOf(Breed::class.java)

    }
}