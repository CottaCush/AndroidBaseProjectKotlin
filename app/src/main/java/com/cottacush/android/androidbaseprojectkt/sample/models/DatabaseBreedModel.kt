/**
 * Copyright (c) 2019 Cotta & Cush Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cottacush.android.androidbaseprojectkt.sample.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.cottacush.android.androidbaseprojectkt.sample.Weight
import com.google.gson.Gson

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

    val wikipediaUrl: String?
)

class WeightConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun toWeight(weight: String): Weight {
        return gson.fromJson(weight, Weight::class.java)
    }

    @TypeConverter
    fun toString(weight: Weight): String {
        return gson.toJson(weight)
    }
}

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
