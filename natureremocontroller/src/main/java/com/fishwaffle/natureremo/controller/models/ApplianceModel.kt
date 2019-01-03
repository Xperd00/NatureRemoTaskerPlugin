/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import java.io.Serializable

/**
 * Model(プリセット情報)
 * @param id プリセットID
 * @param manufacturer メーカー
 * @param remote_name リモコンの型番
 * @param series シリーズ
 * @param name プリセット名
 * @param image アイコン
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class ApplianceModel(var id: String?, var manufacturer: String?, var remote_name: String?, var series: String?, var name: String?, var image: String?) : Serializable