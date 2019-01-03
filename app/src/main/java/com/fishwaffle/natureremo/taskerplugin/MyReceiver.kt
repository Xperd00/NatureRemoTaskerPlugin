/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import com.fishwaffle.natureremo.controller.NatureRemo
import kotlin.concurrent.thread

class MyReceiver : BroadcastReceiver() {
    private val handler = Handler()
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(FIRE_SETTING, ignoreCase = true)) {
            val bundle = intent.getBundleExtra(EXTRA_BUNDLE)

            val type = bundle.getString(BUNDLE_TYPE).let {
                if (it != null) Type.valueOf(it) else null
            }

            when (type) {
                Type.SignalSend -> {
                    val signal = bundle.getString(BUNDLE_SIGNAL_ID)
                    thread {
                        NatureRemo.signalsSignalSendPost(getToken(context), signal)
                        handler.post { Toast.makeText(context, "SignalSend", Toast.LENGTH_SHORT).show() }
                    }
                }
                Type.AirConPowerOff -> {
                    val appliancesId = bundle.getString(BUNDLE_APPLIANCE_ID)
                    thread {
                        NatureRemo.appliancesApplianceAirConSettingsPost(getToken(context), appliancesId,
                                null, null, null, null,
                                "power-off")
                        handler.post { Toast.makeText(context, "AirConPowerOff", Toast.LENGTH_SHORT).show() }
                    }

                }
                Type.AirConSettings -> {
                    val appliancesId = bundle.getString(BUNDLE_APPLIANCE_ID)
                    val mode = bundle.getString(BUNDLE_MODE)
                    val temperature = if (bundle.containsKey(BUNDLE_TEMPERATURE)) bundle.getString(BUNDLE_TEMPERATURE) else null
                    val volume = if (bundle.containsKey(BUNDLE_AIR_VOLUME)) bundle.getString(BUNDLE_AIR_VOLUME) else null
                    val direction = if (bundle.containsKey(BUNDLE_AIR_DIRECTION)) bundle.getString(BUNDLE_AIR_DIRECTION) else null

                    thread {
                        NatureRemo.appliancesApplianceAirConSettingsPost(getToken(context), appliancesId,
                                temperature, mode, volume, direction,
                                "")
                        handler.post { Toast.makeText(context, "AirConSettings", Toast.LENGTH_SHORT).show() }

                    }
                }

            }
        }
    }
}
