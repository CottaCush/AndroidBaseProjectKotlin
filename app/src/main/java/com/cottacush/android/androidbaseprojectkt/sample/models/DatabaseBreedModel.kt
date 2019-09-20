package com.cottacush.android.androidbaseprojectkt.sample.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class DatabaseBreedModel(
    @PrimaryKey
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

fun List<DatabaseBreedModel>.asDomainModel(): List<Breed> {
    return map {
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