package com.cottacush.android.androidbaseprojectkt.sample

import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import com.cottacush.android.androidbaseprojectkt.sample.models.DatabaseBreedModel



data class NetWorkBreedContainer(val breed: List<Breed>)


fun NetWorkBreedContainer.asDatabaseModel(): Array<DatabaseBreedModel> {
    return breed.map {
        DatabaseBreedModel(
            id = it.id,

            description = it.description,

            lifeSpan = it.lifeSpan,

            name = it.name,

            origin = it.origin,

            socialNeeds = it.socialNeeds,
            strangerFriendly = it.strangerFriendly,

            temperament = it.temperament,

            weight = it.weight,

            wikipediaUrl = it.wikipediaUrl
        )
    }.toTypedArray()
}

