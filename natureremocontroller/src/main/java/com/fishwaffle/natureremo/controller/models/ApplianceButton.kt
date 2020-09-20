/*
 * Copyright (c) 2020 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo

/**
 * ボタン情報
 * @param name ボタン名
 * @param image ボタンアイコン
 * @param label ラベル
 */
@JsonIgnoreProperties(ignoreUnknown = NatureRemo.isIgnoreUnknown)
open class ApplianceButton(open var name: String?, open var image: String?, open var label: String?) : Command(Button) {
    override fun getTitle() : String {
        return label!!
    }
}