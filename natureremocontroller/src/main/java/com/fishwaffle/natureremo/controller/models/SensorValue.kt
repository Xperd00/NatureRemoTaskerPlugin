/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import java.io.Serializable

/** センサーの値
 * @param value 値
 * @param created_at 作成日時
 *
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class SensorValue(@JsonProperty("val") var value: Float, var created_at: String?) : Serializable