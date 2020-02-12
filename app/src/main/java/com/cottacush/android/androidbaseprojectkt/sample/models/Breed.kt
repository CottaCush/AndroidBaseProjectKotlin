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
import android.os.Parcelable
import com.cottacush.android.androidbaseprojectkt.sample.Weight
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Breed(
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
) : Parcelable
