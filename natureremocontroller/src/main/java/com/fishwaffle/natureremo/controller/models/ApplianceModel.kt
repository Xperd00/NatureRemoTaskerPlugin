/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable

/**
 * Model(プリセット情報)
 */
class ApplianceModel : Serializable {
    /** プリセットID  */
    var id: String? = null
    /** メーカー  */
    var manufacturer: String? = null
    /** リモコンの型番  */
    var remote_name: String? = null
    /** シリーズ  */
    var series: String? = null
    /** プリセット名  */
    var name: String? = null
    /** アイコン  */
    var image: String? = null

    override fun toString(): String {
        return "ApplianceModel{" + "id='" + id + '\''.toString() + ", manufacturer='" + manufacturer + '\''.toString() + ", remote_name='" + remote_name + '\''.toString() + ", series='" + series + '\''.toString() + ", name='" + name + '\''.toString() + ", image='" + image + '\''.toString() + '}'.toString()
    }
}