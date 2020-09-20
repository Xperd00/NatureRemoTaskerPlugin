/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin

import android.content.Intent
import android.os.Bundle
import com.fishwaffle.natureremo.controller.models.*


const val FIRE_SETTING = "com.twofortyfouram.locale.intent.action.FIRE_SETTING"

/** Taskerに表示される説明 */
const val EXTRA_BLURB = "com.twofortyfouram.locale.intent.extra.BLURB"

/** Receiver受信時に必要な値をここに格納 */
const val EXTRA_BUNDLE = "com.twofortyfouram.locale.intent.extra.BUNDLE"

/** 種別 */
const val BUNDLE_TYPE = "com.fishwaffle.natureremo.TYPE"


const val BUNDLE_SIGNAL_ID = "com.fishwaffle.natureremo.SIGNAL_ID"

const val BUNDLE_BUTTON = "com.fishwaffle.natureremo.BUTTON"

//エアコン関連
const val BUNDLE_APPLIANCE_ID = "com.fishwaffle.natureremo.APPLIANCE_ID"
const val BUNDLE_MODE = "com.fishwaffle.natureremo.MODE"
const val BUNDLE_AIR_VOLUME = "com.fishwaffle.natureremo.AIR_VOLUME"
const val BUNDLE_TEMPERATURE = "com.fishwaffle.natureremo.TEMPERATURE"
const val BUNDLE_AIR_DIRECTION = "com.fishwaffle.natureremo.AIR_DIRECTION"

enum class Type {
    SIGNAL, AirConSettings, AirConPowerOff, TV, LIGHT
}

fun createTaskerDataSend(appliance: Appliance, command: Command): Intent {
    return Intent().apply {
        val bundle = Bundle().apply {
            if (command.type == Command.Signal) {
                putString(BUNDLE_TYPE, Type.SIGNAL.toString())
                putString(BUNDLE_SIGNAL_ID, (command as Signal).id)
            }
            else {
                putString(BUNDLE_TYPE, appliance.type)
                putString(BUNDLE_APPLIANCE_ID, appliance.id)
                putString(BUNDLE_BUTTON, (command as ApplianceButton).name)
            }
        }
        putExtra(EXTRA_BUNDLE, bundle)
        putExtra(EXTRA_BLURB, "${appliance.nickname} : ${command.getTitle()}")
    }
}

fun createTaskerDataAirConPowerOff(applianceName: String, applianceId: String): Intent {
    return Intent().apply {
        val bundle = Bundle().apply {
            putString(BUNDLE_TYPE, Type.AirConPowerOff.toString())
            putString(BUNDLE_APPLIANCE_ID, applianceId)
        }
        putExtra(EXTRA_BUNDLE, bundle)
        putExtra(EXTRA_BLURB, "$applianceName : 電源OFF")
    }

}

fun createTaskerDataAirConSettings(applianceName: String, applianceId: String, mode: String, temperature: String, volume: String, direction: String): Intent {
    return Intent().apply {
        val bundle = Bundle().apply {
            putString(BUNDLE_TYPE, Type.AirConSettings.toString())
            putString(BUNDLE_APPLIANCE_ID, applianceId)
            putString(BUNDLE_MODE, mode)
            if (temperature.isNotBlank()) putString(BUNDLE_TEMPERATURE, temperature)
            if (volume.isNotBlank()) putString(BUNDLE_AIR_VOLUME, volume)
            if (direction.isNotBlank()) putString(BUNDLE_AIR_DIRECTION, direction)
        }
        putExtra(EXTRA_BUNDLE, bundle)
        putExtra(EXTRA_BLURB, "$applianceName : mode=$mode 温度=$temperature 風量=$volume 風向き=$direction")
    }

}