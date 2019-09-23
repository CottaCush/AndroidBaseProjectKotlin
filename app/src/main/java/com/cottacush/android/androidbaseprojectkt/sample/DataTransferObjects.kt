package com.cottacush.android.androidbaseprojectkt.sample

import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import com.cottacush.android.androidbaseprojectkt.sample.models.DatabaseBreedModel
import com.cottacush.android.androidbaseprojectkt.sample.models.Weight


data class NetWorkBreedContainer(val breed: List<NetworkBreedModel>)

data class NetworkBreedModel(
    val id: String,

    val description: String,

    val lifeSpan: String,

    val name: String,

    val origin: String,

    val socialNeeds: Int,

    val strangerFriendly: Int,

    val temperament: String,

    val weight: Weight,

    val wikipediaUrl: String
)


fun NetWorkBreedContainer.asDomainModel(): List<Breed> {
    return breed.map {
        Breed(
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
    }
}


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

